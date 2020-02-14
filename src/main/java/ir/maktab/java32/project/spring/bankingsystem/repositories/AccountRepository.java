package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Account;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class AccountRepository extends CrudRepository<Account, String> {
    private static AccountRepository accountRepository;

    private AccountRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static AccountRepository getInstance() {
        if (accountRepository == null) {
            accountRepository = new AccountRepository();
        }
        return accountRepository;
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }
}
