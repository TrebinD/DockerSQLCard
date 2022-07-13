package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

public class Verification {
    private SelenideElement verCode = $x("//span[@data-test-id='code']//input");
    private SelenideElement buttonVerify = $x("//button[@data-test-id=\"action-verify\"]");
    private SelenideElement errorEmptyField = $x("//span[@class=\"input__sub\"]");
    private SelenideElement errorWrongCode = $x("//div[@class=\"notification__content\"]");


    public void input(String code) {
        verCode.val(code);
        buttonVerify.click();
    }


    public void emptyField() {
        errorEmptyField.should(text("Поле обязательно для заполнения"));
    }

    public void failedCode() {
        errorWrongCode.should(visible);
        errorWrongCode.should(text("Ошибка! Неверно указан код! Попробуйте ещё раз."));
    }

}
