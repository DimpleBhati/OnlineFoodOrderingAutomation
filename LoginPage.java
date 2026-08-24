package foodordering.automation.OnlineFoodOrderingAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(
            WebDriver driver,
            int waitTime) {

        this.driver = driver;

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(waitTime)
        );
    }

    public boolean openLoginIfAvailable() {

        String[] loginXpaths = {

            "//*[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),'login')]",

            "//*[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),'sign in')]"
        };

        for (String xpath : loginXpaths) {

            try {

                for (WebElement element :
                        driver.findElements(By.xpath(xpath))) {

                    if (element.isDisplayed()) {

                        element.click();

                        Thread.sleep(1000);

                        return true;
                    }
                }

            } catch (Exception ignored) {
            }
        }

        return false;
    }

    public boolean isLoginPageDisplayed() {

        String page =
                driver.getPageSource().toLowerCase();

        return page.contains("mobile")
                || page.contains("phone")
                || page.contains("login")
                || page.contains("sign in");
    }
}