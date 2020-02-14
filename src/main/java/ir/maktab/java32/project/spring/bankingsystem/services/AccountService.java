package ir.maktab.java32.project.spring.bankingsystem.services;

import ir.maktab.java32.project.spring.bankingsystem.exceptions.AccountException;
import ir.maktab.java32.project.spring.bankingsystem.models.Account;

public interface AccountService {
    Account createAccount(String nationalCode, Account account) throws AccountException;

    Account deposit(String accountNumber, Long balance) throws AccountException;

    Account withdraw(String accountNumber, Long balance) throws AccountException;

    Account findAccount(String accountNumber) throws AccountException;

    void deleteAccount(String accountNumber) throws AccountException;
}
