package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Person;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class PersonRepository extends CrudRepository<Person, Long> {
    private static PersonRepository personRepository;

    private PersonRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static PersonRepository getInstance() {
        if (personRepository == null) {
            personRepository = new PersonRepository();
        }
        return personRepository;
    }

    @Override
    protected Class<Person> getEntityClass() {
        return Person.class;
    }
}
