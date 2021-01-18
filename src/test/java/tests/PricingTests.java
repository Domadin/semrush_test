package tests;

import com.codeborne.selenide.SelenideElement;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PricingTests extends BaseTestConfig {
    @Test(description = "Проверка ценовых значений таблицы Plans & Pricing по умолчанию (ежемесячно)")
    public void testPricingDefault() {
        open("/prices");

        $(by("data-test", "plan-card__pro"))
                .shouldHave(text("$119"))
                .shouldHave(text(".95"))
                .shouldHave(text("per month"));

        $(by("data-test", "plan-card__guru"))
                .shouldHave(text("$229"))
                .shouldHave(text(".95"))
                .shouldHave(text("per month"));

        $(by("data-test", "plan-card__business"))
                .shouldHave(text("$449"))
                .shouldHave(text(".95"))
                .shouldHave(text("per month"));
    }

    @Test(description = "Проверка ценовых значений таблицы Plans & Pricing при оплате за год")
    public void testPricingAnnual() {
        open("/prices");
        $(by("data-test", "pill-annual")).click();

        $(by("data-test", "plan-card__pro"))
                .shouldHave(text("$99"))
                .shouldHave(text(".95"))
                .shouldHave(text("per month"));

        $(by("data-test", "plan-card__guru"))
                .shouldHave(text("$191"))
                .shouldHave(text(".62"))
                .shouldHave(text("per month"));

        $(by("data-test", "plan-card__business"))
                .shouldHave(text("$374"))
                .shouldHave(text(".95"))
                .shouldHave(text("per month"));
    }

    @Test(description = "Проверка значений тарифов Feature comparison для ряда Historical data")
    public void testHistoricalData() {
        open("/prices");
        $(by("data-test", "pill-annual")).click();

        SelenideElement historicalDataCell =
                $(by("data-test", "comparison-table"))
                        .scrollTo().$(byText("Historical data"));


        historicalDataCell.$(by("data-name", "Info"))
                .hover();
        $(byText("Go back in time and gain insight into your or your" +
                " competitors websites’ performance with Semrush historical data" +
                " gathered since 2012.")).shouldBe(visible);

        historicalDataCell.sibling(0).$("img")
                .shouldBe(image)
                .shouldHave(attribute("alt", "no"));

        historicalDataCell.sibling(1).$("img")
                .shouldBe(image)
                .shouldHave(attribute("alt", "yes"));

        historicalDataCell.sibling(2).$("img")
                .shouldBe(image)
                .shouldHave(attribute("alt", "yes"));
    }
}
