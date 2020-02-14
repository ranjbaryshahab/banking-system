package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Employee;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class EmployeeRepository extends CrudRepository<Employee, Long> {
    private static EmployeeRepository employeeRepository;

    private EmployeeRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static EmployeeRepository getInstance() {
        if (employeeRepository == null) {
            employeeRepository = new EmployeeRepository();
        }
        return employeeRepository;
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
