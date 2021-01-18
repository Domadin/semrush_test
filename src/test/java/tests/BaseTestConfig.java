package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public abstract class BaseTestConfig {

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        Configuration.baseUrl = "https://www.semrush.com";

        //Browser settings
        Configuration.browserSize = "1920x1080";
        if ("remote".equals(System.getProperty("remote"))) {
            Configuration.remote = "some selenoin/moon url";
        }

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("enableVNC", true);
        dc.setCapability("enableVideo", false);
        Configuration.browserCapabilities = dc;

        //Allure listener
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true).savePageSource(true).includeSelenideSteps(true));
    }

    @AfterSuite(alwaysRun = true)
    public void removeListener() {
        SelenideLogger.removeListener("allure");
    }
}
