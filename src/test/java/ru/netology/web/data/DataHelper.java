package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthorizationInfo {
        private String login;
        private String password;

    }

    public static AuthorizationInfo getAuthorizationInfo() {
        return new AuthorizationInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCode() {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String number;

    }

    public static Card getFirstCard() {
        return new Card("5559 0000 0000 0001");
    }

    public static Card getSecondCard() {
        return new Card("5559 0000 0000 0002");
    }
}

