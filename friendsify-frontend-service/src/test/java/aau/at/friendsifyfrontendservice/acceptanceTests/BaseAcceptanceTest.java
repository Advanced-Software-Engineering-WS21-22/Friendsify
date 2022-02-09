package aau.at.friendsifyfrontendservice.acceptanceTests;

import lombok.Getter;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Disabled
@Getter
public abstract class BaseAcceptanceTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Uni\\Selenium\\driver\\chromedriver97.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
