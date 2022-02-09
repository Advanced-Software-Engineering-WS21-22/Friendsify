package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

@Disabled
public class AnniversaryCheckTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void untitled() {
        driver.get("http://localhost:9000/friendsify/login");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.findElement(By.name("username")).click();
        driver.findElement(By.name("username")).sendKeys("max@mustermann.de");
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("password");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Assertions.assertEquals("http://localhost:9000/friendsify/home", driver.getCurrentUrl());
        driver.findElement(By.linkText("Friends")).click();
        driver.findElement(By.cssSelector("#table_entry_childs_button_3 > .fa-info-circle")).click();

        List<WebElement> elements = driver.findElements(By.cssSelector(".col-md-6"));
        String anniversaryText = elements.get(7).getText();

        driver.findElement(By.linkText("Home")).click();
        driver.findElement(By.cssSelector("input:nth-child(2)")).click();
        driver.close();

        String expectedStartChars = "Days until anniversary:";
        Assertions.assertEquals(8, elements.size());
        Assertions.assertTrue(anniversaryText.startsWith(expectedStartChars));
    }

}
