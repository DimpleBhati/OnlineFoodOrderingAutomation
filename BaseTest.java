package foodordering.automation.OnlineFoodOrderingAutomation;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected Properties properties;

    protected int WAIT_TIME;
    protected int PAUSE_TIME;

    @BeforeMethod
    public void setUp() throws Exception {

        properties = new Properties();

        File configFile =
                new File("resources/config.properties");

        if (!configFile.exists()) {
            throw new RuntimeException(
                "config.properties not found at: "
                + configFile.getAbsolutePath()
            );
        }

        FileInputStream input =
                new FileInputStream(configFile);

        properties.load(input);
        input.close();

        WAIT_TIME = Integer.parseInt(
                properties.getProperty("wait", "20")
        );

        PAUSE_TIME = Integer.parseInt(
                properties.getProperty("pause", "3")
        );

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(3));

        driver.get(
                properties.getProperty(
                        "url",
                        "https://eatclub.in/search"
                )
        );
    }

    @AfterMethod
    public void tearDown(ITestResult result)
            throws InterruptedException {

        if (!result.isSuccess() && driver != null) {

            try {

                File source =
                    ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

                File folder =
                    new File("screenshots");

                if (!folder.exists()) {
                    folder.mkdirs();
                }

                File destination =
                    new File(
                        folder,
                        result.getName() + ".png"
                    );

                Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
                );

                System.out.println(
                    "Screenshot saved: "
                    + destination.getAbsolutePath()
                );

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Thread.sleep(PAUSE_TIME * 1000L);

        if (driver != null) {
            driver.quit();
        }
    }
}