package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Customer;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepository extends CrudRepository<Customer, Long> {
    private static CustomerRepository customerRepository;

    private CustomerRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static CustomerRepository getInstance() {
        if (customerRepository == null) {
            customerRepository = new CustomerRepository();
        }
        return customerRepository;
    }

    @Override
    protected Class<Customer> getEntityClass() {
        return Customer.class;
    }

    public Customer findByNationalCode(String nationalCode) {
        session.beginTransaction();
        Query<Customer> query = session.createNativeQuery("select r.ID from ROLE r join PERSON p on r.person_ID=p.ID where r.ROLE_TYPE='CUSTOMER' and p.NATIONAL_CODE=?");
        query.setParameter(1, nationalCode);
        List<Customer> customers = query.list();
        session.getTransaction().commit();
        if (customers != null)
            return customers.get(0);
        return null;
    }
}
