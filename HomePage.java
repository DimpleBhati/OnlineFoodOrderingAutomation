package foodordering.automation.OnlineFoodOrderingAutomation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By searchBox =
        By.xpath("//input[@placeholder='Search for Dishes']");

    public HomePage(
            WebDriver driver,
            int waitTime) {

        this.driver = driver;

        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(waitTime)
        );
    }

    public boolean isHomePageDisplayed() {

        return driver.getCurrentUrl()
                .contains("eatclub.in");
    }

    public void searchFood(String food) {

        WebElement search =
            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    searchBox
                )
            );

        search.click();
        search.clear();
        search.sendKeys(food);
        search.sendKeys(Keys.ENTER);

        wait.until(
            d -> d.getCurrentUrl()
                    .contains("search")
                    || d.findElements(searchBox).size() > 0
        );
    }
}