package test;

import data.UserData;
import dataHelper.DataHelper;
import dataHelper.SQLHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.Dashboard;
import page.Login;
import page.Verification;

import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {
    UserData userData;
    Login login;


    @BeforeEach
    public void setup() {
        open("http://localhost:9999/");
        userData = DataHelper.validUser();
        login = new Login();
    }

    @AfterEach
    public void after() {
        SQLHelper.resetStatus(userData.getName());
        SQLHelper.resetVerifyCode();
    }

    @Test
    public void validData() {
        login.input(userData.getName(), userData.getPassword());
        Verification verification = new Verification();
        verification.input(DataHelper.validVerifyCode(userData.getName()));
        Dashboard dashboard = new Dashboard();
        dashboard.visiblePage();
    }


    @Test
    public void emptyData() {
        login.input(null, null);
        login.emptyData();
    }

    @Test
    public void emptyDataLogin() {
        login.input(null, userData.getPassword());
        login.emptyLogin();
    }


    @Test
    public void emptyDataPassword() {
        login.input(userData.getName(), null);
        login.emptyPassword();
    }

    @Test
    public void wrongLogin() {
        login.input("Yilia", userData.getPassword());
        login.failedInputData();
    }

    @Test
    public void blockedUser() {
        login.input(userData.getName(), DataHelper.randomPass());
        login.failedInputData();
        login.input(userData.getName(), DataHelper.randomPass());
        login.failedInputData();
        login.input(userData.getName(), DataHelper.randomPass());
        login.failedInputData();
        login.input(userData.getName(), DataHelper.randomPass());
        login.failedInputData();
        DataHelper.assertStatus(userData.getName());
    }

    @Test
    public void emptyVerifyCode() {
        login.input(userData.getName(), userData.getPassword());
        Verification verification = new Verification();
        verification.input(null);
        verification.emptyField();
    }

    @Test
    public void wrongVerifyCode() {
        login.input(userData.getName(), userData.getPassword());
        Verification verification = new Verification();
        verification.input(DataHelper.invalidVerifyCode());
        verification.failedCode();
    }


}
