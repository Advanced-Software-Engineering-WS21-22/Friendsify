package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.junit.Before;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

@Disabled
public abstract class BaseLoggedInAcceptanceTest extends BaseAcceptanceTest {

    @Before
    public void setUp() {
        super.setUp();
        login();
    }

    protected void login() {
        getDriver().get("http://localhost:9000/friendsify");
        getDriver().manage().window().setSize(new Dimension(2576, 1408));
        getDriver().findElement(By.name("username")).click();
        getDriver().findElement(By.name("username")).sendKeys("max@mustermann.de");
        getDriver().findElement(By.name("password")).click();
        getDriver().findElement(By.name("password")).sendKeys("password");
        getDriver().findElement(By.name("password")).sendKeys(Keys.ENTER);
    }
}
