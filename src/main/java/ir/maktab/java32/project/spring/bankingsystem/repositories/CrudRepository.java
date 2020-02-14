package ir.maktab.java32.project.spring.bankingsystem.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class CrudRepository<Entity, ID extends Serializable> {
    protected abstract Class<Entity> getEntityClass();

    Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public Entity save(Entity entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        return entity;
    }

    public Entity update(Entity entity) {
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        return entity;
    }

    public void remove(Entity entity) {
        session.beginTransaction();
        session.remove(entity);
        session.getTransaction().commit();
    }

    public void removeById(ID id) {
        Entity entity = findById(id);
        if (entity != null) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }

    public Entity findById(ID id) {
        session.beginTransaction();
        Entity entity = session.get(getEntityClass(), id);
        session.getTransaction().commit();
        return entity;
    }

    public List<Entity> findAll() {
        session.beginTransaction();
        Query<Entity> query = session
                .createQuery("from " + getEntityClass().getName(), getEntityClass());
        List<Entity> entities = query.list();
        session.getTransaction().commit();
        return entities;
    }

    public List<Entity> findAll(int start, int row) {
        session.beginTransaction();
        Query<Entity> query = session
                .createQuery("from " + getEntityClass().getName(), getEntityClass());
        query.setFirstResult(start);
        query.setMaxResults(row);
        List<Entity> entities = query.list();
        session.getTransaction().commit();
        return entities;
    }

    public List<Entity> findAll(Predicate<Entity> predicate) {
        List<Entity> allEntities = findAll();
        List<Entity> result = new ArrayList<>();
        session.beginTransaction();
        allEntities.stream().filter(predicate).forEach(result::add);
        session.getTransaction().commit();
        return result;
    }

    public <T> List<T> findAll(Function<Entity, T> function) {
        List<Entity> allEntities = findAll();
        List<T> result = new ArrayList<>();
        session.beginTransaction();
        allEntities.stream().map(function).forEach(result::add);
        session.getTransaction().commit();
        return result;
    }
}
