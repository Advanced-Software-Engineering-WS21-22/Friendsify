package aau.at.friendsifyfrontendservice.acceptanceTests;

import lombok.Getter;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
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
        login();
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    public void login() {
        driver.get("http://localhost:9000/friendsify/login");
        driver.manage().window().setSize(new Dimension(2576, 1408));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("max@mustermann.de");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
    }
}
