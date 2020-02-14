package ir.maktab.java32.project.spring.bankingsystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.java32.project.spring.bankingsystem.enumuration.TransactionType;
import ir.maktab.java32.project.spring.bankingsystem.exceptions.AccountException;
import ir.maktab.java32.project.spring.bankingsystem.exceptions.CardException;
import ir.maktab.java32.project.spring.bankingsystem.models.Account;
import ir.maktab.java32.project.spring.bankingsystem.models.Card;
import ir.maktab.java32.project.spring.bankingsystem.models.Transaction;
import ir.maktab.java32.project.spring.bankingsystem.repositories.CardRepository;
import ir.maktab.java32.project.spring.bankingsystem.repositories.TransactionRepository;
import ir.maktab.java32.project.spring.bankingsystem.utils.AuthenticationService;

import java.util.Date;

public class CardServiceImpl implements CardService {
    private AccountService accountService = new AccountServiceImpl();

    @Override
    public Card createCard(String accountNumber, Card card) throws AccountException {
        Account account;
        if (card != null) {
            if ((account = new AccountServiceImpl().findAccount(accountNumber)) != null) {
                card.setAccount(account);
                return CardRepository.getInstance().save(card);
            }
        }
        return null;
    }

    @Override
    public Transaction transfer(String toCardNumber, Long balance) throws CardException, AccountException {
        if (AuthenticationService.getInstance().getLoginCard() != null) {
            Card toCard = new Card();
            toCard.setCardNumber(toCardNumber);
            Transaction transaction = new Transaction();
            transaction.setDate(new Date());
            transaction.setBalance(balance);
            transaction.setTransactionType(TransactionType.TRANSFER_TO_CARD);
            transaction.setTo(toCard);
            transaction.setFrom(AuthenticationService.getInstance().getLoginCard());
            transaction.setIsSuccessful(false);
            if ((toCard = findCard(toCardNumber)) != null) {
                accountService.withdraw(AuthenticationService.getInstance().getLoginCard().getAccount().getAccountNumber(), balance);
                accountService.deposit(toCard.getAccount().getAccountNumber(), balance);
                transaction.setIsSuccessful(true);
                transaction.setTo(toCard);
            } else throw new CardException("This card number is not available in the system !!!");
            return TransactionRepository.getInstance().save(transaction);
        } else throw new CardException("Please login in the system !!!");
    }

    @Override
    public void changeFirstPassword(String newPassword) {
        AuthenticationService.getInstance().getLoginCard().getCardPasswordInfo().setPassword(newPassword);
        CardRepository.getInstance().update(AuthenticationService.getInstance().getLoginCard());
    }

    @Override
    public void changeSecondPassword(String newPassword) {
        AuthenticationService.getInstance().getLoginCard().getCardPasswordInfo().setSecondPassword(newPassword);
        CardRepository.getInstance().update(AuthenticationService.getInstance().getLoginCard());
    }

    @Override
    public Card findCard(String cardNumber) throws CardException {
        if (cardNumber.length() == 16) {
            Card card;
            if ((card = CardRepository.getInstance().findById(cardNumber)) != null)
                return card;
            else throw new CardException("This card number is not available in the system !!!");
        } else throw new CardException("The card number entered is not correct !!!");
    }

    @Override
    public Long getBalance() {
        return AuthenticationService.getInstance().getLoginCard().getAccount().getBalance();
    }

    @Override
    public Transaction withdraw(Long balance) {
        Transaction transaction = new Transaction();
        transaction.setFrom(AuthenticationService.getInstance().getLoginCard());
        transaction.setTo(AuthenticationService.getInstance().getLoginCard());
        transaction.setDate(new Date());
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setBalance(balance);
        transaction.setIsSuccessful(false);
        Account withdraw = null;
        try {
            withdraw = accountService.withdraw(AuthenticationService.getInstance().getLoginCard().getAccount().getAccountNumber(), balance);
        } catch (AccountException e) {
            System.out.println(e.getMessage());
        }


        if (withdraw != null)
            transaction.setIsSuccessful(true);


        return TransactionRepository.getInstance().save(transaction);
    }

    @Override
    public Card login(String cardNumber, String password) throws CardException {
        Card card;
        if ((card = findCard(cardNumber)) != null) {
            if (card.getCardPasswordInfo().getPassword().equals(password)) {
                AuthenticationService.getInstance().setLoginCard(card);
                return card;
            }
        }
        return null;
    }
}
