package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class FeaturesTests extends BaseTestConfig {
    @Test(description = "Открытие страницы Features через пункт навбара")
    public void testOpenFeatures() {
        open("/");
        $(by("data-test", "header_features")).click();

        assertEquals(Selenide.title(), "Features | SEMrush");
        assertEquals(WebDriverRunner.url(), Configuration.baseUrl + "/features/");
    }
}
