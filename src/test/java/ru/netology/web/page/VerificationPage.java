package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement wrongCodeErrorMessage = $("[data-test-id='error-notification']");
    private SelenideElement emptyCodeErrorMessage = $("[data-test-id='code'] span.input__sub");

    public VerificationPage() {
    }

    public DashboardPage validVerify(String code) {

        codeField.setValue(code);
        verifyButton.click();
        return new DashboardPage();

    }

    public boolean isWrongCodeError() {

        return wrongCodeErrorMessage.exists();

    }

    public boolean isEmptyCodeError() {

        return emptyCodeErrorMessage.exists();

    }

}
