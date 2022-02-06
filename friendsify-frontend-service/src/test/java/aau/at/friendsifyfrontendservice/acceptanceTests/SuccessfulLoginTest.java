package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;

@Disabled
public class SuccessfulLoginTest {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firsttest() {
        driver.get("http://localhost:9000/friendsify/login");
        driver.manage().window().setSize(new Dimension(2576, 1408));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("max@mustermann.de");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("Friends")).click();
        String url = driver.getCurrentUrl();
        driver.close();
        Assert.assertEquals("http://localhost:9000/friendsify/friends", url);
    }


     /*
        WDS.sampleResult.sampleStart()
        WDS.browser.get("http://localhost:9000/friendsify/login");
        WDS.browser.manage().window().setSize(new org.openqa.selenium.Dimension(2576, 1408));
        WDS.browser.findElement(org.openqa.selenium.By.name("username")).click();
        WDS.browser.findElement(org.openqa.selenium.By.name("username")).sendKeys("max@mustermann.de");
        WDS.browser.findElement(org.openqa.selenium.By.name("password")).click();
        WDS.browser.findElement(org.openqa.selenium.By.name("password")).sendKeys("password");
        WDS.browser.findElement(org.openqa.selenium.By.name("password")).sendKeys(org.openqa.selenium.Keys.ENTER);
        WDS.browser.findElement(org.openqa.selenium.By.linkText("Friends")).click();
        WDS.sampleResult.sampleEnd()
      */
}
