package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;
import java.sql.SQLException;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    private final DataHelper dataHelper = new DataHelper();

    @Test
    void shouldLoginUser() throws SQLException {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getValidUserInfo(0);
        val verificationPage = loginPage.validLogin(userInfo);
        val verificationCode = dataHelper.getVerificationCodeFor(userInfo);
        verificationPage.validVerify(verificationCode);
        dataHelper.clearVerificationCode(verificationCode);

    }

    @Test
    void shouldLoginAnotherUser() throws SQLException {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getValidUserInfo(1);
        val verificationPage = loginPage.validLogin(userInfo);
        val verificationCode = dataHelper.getVerificationCodeFor(userInfo);
        verificationPage.validVerify(verificationCode);
        dataHelper.clearVerificationCode(verificationCode);

    }

    @Test
    void shouldAlertWhenWrongUser() {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getInValidUserInfo();
        val verificationPage = loginPage.validLogin(userInfo);
        assertTrue(loginPage.isWrongUserError());

    }

    @Test
    void shouldAlertWhenEmptyLogin() {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getEmptyLoginUserInfo();
        val verificationPage = loginPage.validLogin(userInfo);
        assertTrue(loginPage.isEmptyLoginError());

    }

    @Test
    void shouldAlertWhenEmptyPassword() {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getEmptyPasswordUserInfo();
        val verificationPage = loginPage.validLogin(userInfo);
        assertTrue(loginPage.isEmptyPasswordError());

    }

    @Test
    void shouldAlertWhenWrongVerificationCode() throws SQLException {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getValidUserInfo(0);
        val verificationPage = loginPage.validLogin(userInfo);
        val verificationCode = dataHelper.getVerificationCodeFor(userInfo);
        verificationPage.validVerify(verificationCode.substring(2));
        assertTrue(verificationPage.isWrongCodeError());
        dataHelper.clearVerificationCode(verificationCode);

    }

    @Test
    void shouldAlertWhenEmptyVerificationCode() throws SQLException {

        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val userInfo = dataHelper.getValidUserInfo(0);
        val verificationPage = loginPage.validLogin(userInfo);
        val verificationCode = dataHelper.getVerificationCodeFor(userInfo);
        verificationPage.validVerify("");
        assertTrue(verificationPage.isEmptyCodeError());
        dataHelper.clearVerificationCode(verificationCode);

    }

}
