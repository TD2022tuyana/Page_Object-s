package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;


class MoneyTransferTest {
    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }


    @Test
    void shouldTransferMoneySuccessfully() {
        var loginPage = new LoginPage();
        int transferAmount = 1000;
        var authoInfo = DataHelper.getAuthorizationInfo();
        var verificationPage = loginPage.validLogin(authoInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.submitVerificationCode(verificationCode);
        var sourceCardNumber = DataHelper.getSecondCard().getNumber();
        var beforeBalanceSourceCard = dashboardPage.getCardBalance(1);
        var beforeBalanceTargetCard = dashboardPage.getCardBalance(0);
        var moneyTransferPage = dashboardPage.amountCards(0);

        var actualBalance = moneyTransferPage.replenishCard(sourceCardNumber, String.valueOf(transferAmount));
        TestHelper.assertCardBalances(beforeBalanceSourceCard, beforeBalanceTargetCard, transferAmount,
                actualBalance.getCardBalance(1), actualBalance.getCardBalance(0));
    }

    @Test
    void shouldTransferMoneySuccessfullyFromAnotherCard() {
        var loginPage = new LoginPage();
        int transferAmount = 1000;
        var authoInfo = DataHelper.getAuthorizationInfo();
        var verificationPage = loginPage.validLogin(authoInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.submitVerificationCode(verificationCode);
        var sourceCardNumber = DataHelper.getFirstCard().getNumber();
        var beforeBalanceSourceCard = dashboardPage.getCardBalance(0);
        var beforeBalanceTargetCard = dashboardPage.getCardBalance(1);
        var moneyTransferPage = dashboardPage.amountCards(1);

        var actualBalance = moneyTransferPage.replenishCard(sourceCardNumber, String.valueOf(transferAmount));
        TestHelper.assertCardBalances(beforeBalanceSourceCard, beforeBalanceTargetCard, transferAmount,
                actualBalance.getCardBalance(0), actualBalance.getCardBalance(1));
    }

    @Test
    void shouldNotTransferMoneyWhenInsufficientFundsOnSourceCard() {
        var loginPage = new LoginPage();
        int transferAmount = 20000; // more than available on source card
        var authoInfo = DataHelper.getAuthorizationInfo();
        var verificationPage = loginPage.validLogin(authoInfo);
        var verificationCode = DataHelper.getVerificationCode();
        var dashboardPage = verificationPage.submitVerificationCode(verificationCode);
        var sourceCardNumber = DataHelper.getSecondCard().getNumber();
        var beforeBalanceSourceCard = dashboardPage.getCardBalance(1);
        var beforeBalanceTargetCard = dashboardPage.getCardBalance(0);
        var moneyTransferPage = dashboardPage.amountCards(0);

        var actualBalance = moneyTransferPage.replenishCard(sourceCardNumber, String.valueOf(transferAmount));
        TestHelper.assertEqualBalances(beforeBalanceSourceCard, actualBalance.getCardBalance(1), beforeBalanceTargetCard, actualBalance.getCardBalance(0));
        TestHelper.assertInsufficientFundsError(moneyTransferPage);
    }


}
