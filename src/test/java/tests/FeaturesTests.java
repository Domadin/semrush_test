package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;


@Epic("Land page")
@Feature("Features page")
public class FeaturesTests extends BaseTestConfig {

    @Test(description = "Open Features page via navbar")
    public void testOpenFeatures() {
        open("/");
        $(by("data-test", "header_features")).click();

        $(".semrush-features").shouldHave(text("Semrush features"));
        assertEquals(Selenide.title(), "Features | SEMrush");
        assertEquals(WebDriverRunner.url(), Configuration.baseUrl + "/features/");
    }

    @Test(description = "Open Sign up page via a button on Features page")
    public void testOpenSignUp() {
        open("/features");
        $(byText("Sign up for free")).click();

        $(by("data-test", "SignUpForm")).shouldHave(text("Create your account"));
        assertEquals(Selenide.title(), "Sign up – Semrush");
        assertEquals(WebDriverRunner.url(), Configuration.baseUrl + "/signup/?src=features");
    }

    @Test(description = "Features section. Filter Features by Advertising.")
    public void chooseAdvertTab() {
        open("/features");
        $(".semrush-features__choose-tabs").$(byText("Advertising")).scrollTo().click();

        $$(".semrush-features__block").filterBy(visible).shouldHaveSize(2);
        $$(".semrush-features__block").findBy(text("PPC Keyword Research")).shouldBe(visible);
        $$(".semrush-features__block").findBy(text("Website Monetization")).shouldBe(visible);
    }
}
