package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.page.DashboardPage;

import static com.codeborne.selenide.Selenide.$;

public class TransferOfCardsPage {
    private SelenideElement amountField = $(".input[data-test-id='amount'] [class='input__control']");
    private SelenideElement fromField = $(".input[data-test-id='from'] [class='input__control']");
    private SelenideElement transferButton = $("[data-test-id='action-transfer']");

    public DashboardPage replenishCard(String cardNumber, String sum) {
        amountField.setValue(sum);
        fromField.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    public String getErrorMessage() {
        return $(".notification__content").getText();
    }


}