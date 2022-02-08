package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
public class WeatherTest extends BaseLoggedInAcceptanceTest {

    @Test
    public void findWeatherData() {
        boolean result = getDriver().findElement(By.id("weather-data")).isDisplayed();

        assertTrue(result);
    }
}
