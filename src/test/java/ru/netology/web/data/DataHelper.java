package ru.netology.web.data;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataHelper {

    private List<UserInfo> userInfoList;

    public DataHelper() {

        List<UserInfo> userInfoList = new ArrayList<>();

        userInfoList.add(new UserInfo("vasya", "qwerty123"));
        userInfoList.add(new UserInfo("petya", "123qwerty"));

        this.userInfoList = userInfoList;
    }

    @Value
    public class UserInfo {

        private String login;
        private String password;

        UserInfo(String login, String password) {

            this.login = login;
            this.password = password;

        }

    }

    public UserInfo getValidUserInfo(int index) {

        return userInfoList.get(index);

    }

    public UserInfo getInValidUserInfo() {

        return new UserInfo("vanya", "12345");

    }

    public UserInfo getEmptyLoginUserInfo() {

        return new UserInfo("", "qwerty123");

    }

    public UserInfo getEmptyPasswordUserInfo() {

        return new UserInfo("vasya", "");

    }

    public String getVerificationCodeFor(UserInfo userInfo) {

        val getUserIdSQL = "SELECT id FROM users WHERE login = ?";
        val getVerificationCodeSQL = "SELECT code FROM auth_codes WHERE user_id = ? ORDER BY created DESC LIMIT 1";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val userId = runner.query(conn, getUserIdSQL, new ScalarHandler<>(), userInfo.getLogin());
            val verificationCode = runner.query(conn, getVerificationCodeSQL, new ScalarHandler<>(), userId);
            return (String) verificationCode;
        } catch (SQLException e) {
            System.out.println("Ошибка доступа к базе данных");
            System.out.println(e.getMessage());
        }
        return "Что-то пошло не так!";

    }

    public void clearVerificationCode(String code) {

        val clearAuthCodeTable = "DELETE FROM auth_codes WHERE code = ?";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            val rowUpdated = runner.update(conn, clearAuthCodeTable, code);
        } catch (SQLException e) {
            System.out.println("Ошибка доступа к базе данных");
            System.out.println(e.getMessage());
        }
    }

    public static void clearDB() {

        val clearCards = "DELETE FROM cards";
        val clearAuthCodes = "DELETE FROM auth_codes";
        val clearUsers = "DELETE FROM users";
        val runner = new QueryRunner();

        try (
                val conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/app", "app", "pass"
                );
        ) {
            int rowUpdated = runner.update(conn, clearCards);
            rowUpdated = runner.update(conn, clearAuthCodes);
            rowUpdated = runner.update(conn, clearUsers);
        } catch (SQLException e) {
            System.out.println("Ошибка доступа к базе данных");
            System.out.println(e.getMessage());
        }

    }
}
