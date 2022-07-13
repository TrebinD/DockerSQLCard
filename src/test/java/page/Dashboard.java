package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class Dashboard {
    private SelenideElement header = $x("//h2[@data-test-id=\"dashboard\"]");

    public void visiblePage() {
        header.should(visible);
    }
}
