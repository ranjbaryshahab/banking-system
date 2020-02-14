package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Transaction;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class TransactionRepository extends CrudRepository<Transaction, Long> {
    private static TransactionRepository transactionRepository;

    private TransactionRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static TransactionRepository getInstance() {
        if (transactionRepository == null) {
            transactionRepository = new TransactionRepository();
        }
        return transactionRepository;
    }

    @Override
    protected Class<Transaction> getEntityClass() {
        return Transaction.class;
    }
}
