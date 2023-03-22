package ru.netology.web.test;

import ru.netology.web.page.TransferOfCardsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHelper {
    public static void assertEqualBalances(int expectedBalanceSourceCard, int actualBalanceSourceCard, int expectedBalanceTargetCard, int actualBalanceTargetCard) {
        assertEquals(expectedBalanceSourceCard, actualBalanceSourceCard);
        assertEquals(expectedBalanceTargetCard, actualBalanceTargetCard);
    }

    public static void assertCardBalances(int beforeBalanceSourceCard, int beforeBalanceTargetCard, int transferAmount, int actualBalanceSourceCard, int actualBalanceTargetCard) {
        int expectedBalanceSourceCard = beforeBalanceSourceCard - transferAmount;
        int expectedBalanceTargetCard = beforeBalanceTargetCard + transferAmount;
        assertEqualBalances(expectedBalanceSourceCard, actualBalanceSourceCard, expectedBalanceTargetCard, actualBalanceTargetCard);
    }

    public static void assertInsufficientFundsError(TransferOfCardsPage moneyTransferPage) {
        String expectedErrorMessage = "На карте недостаточно средств";
        String actualErrorMessage = moneyTransferPage.getErrorMessage();
        assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Ожидаемое сообщение об ошибке: " + expectedErrorMessage + " но было: " + actualErrorMessage);
    }


}