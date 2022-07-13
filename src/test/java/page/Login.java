package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Login {
    private SelenideElement loginInput = $x("//input[@name=\"login\"]");
    private SelenideElement passwordInput = $x("//input[@name=\"password\"]");
    private SelenideElement buttonLogin = $x("//button[@data-test-id=\"action-login\"]");
    private SelenideElement errorEmptyLogin = $x("//span[@data-test-id=\"login\"]//span[@class=\"input__sub\"]");
    private SelenideElement errorEmptyPassword = $x("//span[@data-test-id=\"password\"]//span[@class=\"input__sub\"]");
    private SelenideElement wrongData = $x("//div[@data-test-id=\"error-notification\"]");
    private SelenideElement buttonError = $x("//button[@role=\"button\"]//span[@class=\"icon-button__content\"]");


    public void input(String login, String password){
        loginInput.val(login);
        passwordInput.val(password);
        buttonLogin.click();
    }

    public void failedInputData(){
        wrongData.should(visible);
        wrongData.$x(".//div[@class = \"notification__content\"]").should(text("Ошибка! Неверно указан логин или пароль"));
        buttonError.should(visible);
        buttonError.click();
        buttonError.should(hidden);
    }

    public void emptyLogin(){
        errorEmptyLogin.should(visible);
        errorEmptyLogin.should(text("Поле обязательно для заполнения"));
    }

    public void emptyPassword(){
        errorEmptyPassword.should(visible);
        errorEmptyPassword.should(text("Поле обязательно для заполнения"));
    }

    public void emptyData(){
        errorEmptyLogin.should(visible);
        errorEmptyPassword.should(visible);
    }

}
