package ir.maktab.java32.project.spring.bankingsystem.services;

import ir.maktab.java32.project.spring.bankingsystem.exceptions.AccountException;
import ir.maktab.java32.project.spring.bankingsystem.models.Account;
import ir.maktab.java32.project.spring.bankingsystem.models.Customer;
import ir.maktab.java32.project.spring.bankingsystem.repositories.AccountRepository;
import ir.maktab.java32.project.spring.bankingsystem.repositories.CustomerRepository;

import java.util.Set;

public class AccountServiceImpl implements AccountService {
    @Override
    public Account createAccount(String nationalCode, Account account) throws AccountException {
        if (account != null) {
            Customer customer;
            if ((customer = CustomerRepository.getInstance().findByNationalCode(nationalCode)) != null) {
                if (customer.getAccounts() != null)
                    customer.getAccounts().add(account);
                customer.setAccounts(Set.of(account));
                CustomerRepository.getInstance().update(customer);
            } else throw new AccountException("This national code is not available in the system !!!");
        }
        return null;
    }

    @Override
    public Account deposit(String accountNumber, Long balance) throws AccountException {
        Account account;
        if ((account = findAccount(accountNumber)) != null) {
            account.setBalance((account.getBalance() + balance));
            return AccountRepository.getInstance().update(account);
        }
        return null;
    }

    @Override
    public Account withdraw(String accountNumber, Long balance) throws AccountException {
        Account account;
        if ((account = findAccount(accountNumber)) != null) {
            if (account.getBalance() >= balance) {
                account.setBalance((account.getBalance() - balance));
                return AccountRepository.getInstance().update(account);
            } else throw new AccountException("Account balance is not enough !!!");
        }
        return null;
    }

    @Override
    public Account findAccount(String accountNumber) throws AccountException {
        Account account;
        if ((account = AccountRepository.getInstance().findById(accountNumber)) != null)
            return account;
        else throw new AccountException("This account number is not available in the system !!!");
    }

    @Override
    public void deleteAccount(String accountNumber) throws AccountException {
        if (findAccount(accountNumber) != null)
            AccountRepository.getInstance().removeById(accountNumber);
    }
}
