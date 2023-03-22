package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

//page for authorization
public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");


    public VerificationPage validLogin(DataHelper.AuthorizationInfo authoInfo) {
        loginField.setValue(authoInfo.getLogin());
        passwordField.setValue(authoInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}