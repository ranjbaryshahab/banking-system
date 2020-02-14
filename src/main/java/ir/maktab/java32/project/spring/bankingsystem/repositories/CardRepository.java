package ir.maktab.java32.project.spring.bankingsystem.repositories;

import ir.maktab.java32.project.spring.bankingsystem.models.Card;
import ir.maktab.java32.project.spring.bankingsystem.utils.HibernateUtil;

public class CardRepository extends CrudRepository<Card, String> {
    private static CardRepository cardRepository;

    private CardRepository() {
        setSession(HibernateUtil.getSession());
    }

    public static CardRepository getInstance() {
        if (cardRepository == null) {
            cardRepository = new CardRepository();
        }
        return cardRepository;
    }

    @Override
    protected Class<Card> getEntityClass() {
        return Card.class;
    }
}
