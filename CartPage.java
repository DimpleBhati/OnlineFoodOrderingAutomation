package foodordering.automation.OnlineFoodOrderingAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(
            WebDriver driver,
            int waitTime) {

        this.driver = driver;

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(waitTime)
        );
    }

    public boolean openCart() {

        String[] cartXpaths = {

            "//*[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),'cart')]",

            "//*[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),'basket')]"
        };

        for (String xpath : cartXpaths) {

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

    public boolean isCartDisplayed() {

        String currentUrl =
                driver.getCurrentUrl().toLowerCase();

        if (currentUrl.contains("cart")) {
            return true;
        }

        String page =
                driver.getPageSource().toLowerCase();

        return page.contains("cart")
                || page.contains("your cart")
                || page.contains("basket");
    }

    public boolean removeItem() {

        String[] removeXpaths = {

            "//*[contains(translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),'remove')]",

            "//*[normalize-space()='−']",

            "//*[normalize-space()='-']"
        };

        for (String xpath : removeXpaths) {

            try {

                for (WebElement element :
                        driver.findElements(By.xpath(xpath))) {

                    if (element.isDisplayed()
                            && element.isEnabled()) {

                        element.click();

                        return true;
                    }
                }

            } catch (Exception ignored) {
            }
        }

        return false;
    }
}