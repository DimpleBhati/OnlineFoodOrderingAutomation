package foodordering.automation.OnlineFoodOrderingAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RestaurantPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By biryaniText =
        By.xpath(
            "//*[contains("
            + "translate(normalize-space(.),"
            + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
            + "'abcdefghijklmnopqrstuvwxyz'),"
            + "'biryani')]"
        );

    public RestaurantPage(
            WebDriver driver,
            int waitTime) {

        this.driver = driver;

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(waitTime)
        );
    }

    public boolean isRestaurantPageDisplayed() {

        try {

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    biryaniText
                )
            );

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean selectRestaurant() {

        try {

            WebElement restaurant =
                wait.until(
                    ExpectedConditions
                        .elementToBeClickable(
                            biryaniText
                        )
                );

            restaurant.click();

            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public String getRestaurantUrl() {

        return driver.getCurrentUrl();
    }
}