package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement wrongUserErrorMessage = $("[data-test-id='error-notification']");
    private SelenideElement emptyLoginErrorMessage = $("[data-test-id='login'] span.input__sub");
    private SelenideElement emptyPasswordErrorMessage = $("[data-test-id='password'] span.input__sub");

    public VerificationPage validLogin(DataHelper.UserInfo info) {

        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();

    }

    public boolean isWrongUserError() {

        return wrongUserErrorMessage.exists();

    }

    public boolean isEmptyLoginError() {

        return emptyLoginErrorMessage.exists();

    }

    public boolean isEmptyPasswordError() {

        return emptyPasswordErrorMessage.exists();

    }

}