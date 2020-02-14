package ir.maktab.java32.project.spring.bankingsystem.utils;


import ir.maktab.java32.project.spring.bankingsystem.models.Card;

public final class AuthenticationService {

    private static AuthenticationService authenticationService;
    private Card cardLogin;

    public static synchronized AuthenticationService getInstance() {
        if (authenticationService == null)
            authenticationService = new AuthenticationService();
        return authenticationService;
    }

    private AuthenticationService() {
    }

    public void setLoginCard(Card card) {
        this.cardLogin = card;
    }

    public Card getLoginCard() {
        return cardLogin;
    }
}
