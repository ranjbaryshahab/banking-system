package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Role;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class RoleRepository extends CrudRepository<Role, Long> {
    private static RoleRepository roleRepository;

    private RoleRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static RoleRepository getInstance() {
        if (roleRepository == null) {
            roleRepository = new RoleRepository();
        }
        return roleRepository;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
