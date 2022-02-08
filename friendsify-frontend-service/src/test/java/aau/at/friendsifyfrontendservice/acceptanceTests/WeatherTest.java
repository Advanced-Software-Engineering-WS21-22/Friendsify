package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class WeatherTest extends BaseAcceptanceTest {

    @Test
    public void findWeatherData() {
        assertTrue(getDriver().findElement(By.id("weather-data")).isDisplayed());
    }
}
