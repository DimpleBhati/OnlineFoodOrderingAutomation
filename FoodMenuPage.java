package foodordering.automation.OnlineFoodOrderingAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FoodMenuPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public FoodMenuPage(
            WebDriver driver,
            int waitTime) {

        this.driver = driver;

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(waitTime)
        );
    }

    public boolean isFoodDisplayed(String food) {

        try {

            By foodLocator =
                By.xpath(
                    "//*[contains("
                    + "translate(normalize-space(.),"
                    + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                    + "'abcdefghijklmnopqrstuvwxyz'),"
                    + "'" + food.toLowerCase() + "')]"
                );

            return wait.until(
                ExpectedConditions
                    .visibilityOfElementLocated(foodLocator)
            ).isDisplayed();

        } catch (Exception e) {

            return false;
        }
    }

    public boolean addFood(String food) {

        /*
         * Find the food text first.
         */
        By foodLocator =
            By.xpath(
                "//*[contains("
                + "translate(normalize-space(.),"
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                + "'abcdefghijklmnopqrstuvwxyz'),"
                + "'" + food.toLowerCase() + "')]"
            );

        try {

            WebElement foodElement =
                wait.until(
                    ExpectedConditions
                        .visibilityOfElementLocated(
                            foodLocator
                        )
                );

            /*
             * Scroll the food into view.
             */
            ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    foodElement
                );

            /*
             * Try common Add controls within the
             * product/card area.
             */
            String[] xpaths = {

                "//button[contains(translate(.,"
                + "'ABCDEFGHIJKLMNOPQRSTUVWXYZ',"
                + "'abcdefghijklmnopqrstuvwxyz'),'add')]",

                "//*[normalize-space()='+']",

                "//*[normalize-space()='Add']",

                "//*[normalize-space()='ADD']"
            };

            for (String xpath : xpaths) {

                try {

                    By add =
                        By.xpath(xpath);

                    for (WebElement button :
                            driver.findElements(add)) {

                        if (button.isDisplayed()
                                && button.isEnabled()) {

                            button.click();

                            return true;
                        }
                    }

                } catch (Exception ignored) {
                }
            }

        } catch (Exception ignored) {
        }

        return false;
    }
}