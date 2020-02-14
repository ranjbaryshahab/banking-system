package ir.maktab.java32.project.spring.bankingsystem.services;

import ir.maktab.java32.project.spring.bankingsystem.exceptions.AccountException;
import ir.maktab.java32.project.spring.bankingsystem.exceptions.CardException;
import ir.maktab.java32.project.spring.bankingsystem.models.Card;
import ir.maktab.java32.project.spring.bankingsystem.models.Transaction;

public interface CardService {
    Card createCard(String accountNumber, Card card) throws AccountException;

    Transaction transfer(String toCardNumber, Long balance) throws CardException, AccountException;

    void changeFirstPassword(String newPassword);

    void changeSecondPassword(String newPassword);

    Card findCard(String cardNumber) throws CardException;

    Long getBalance();

    Transaction withdraw(Long balance);

    Card login(String cardNumber, String password) throws CardException;
}
