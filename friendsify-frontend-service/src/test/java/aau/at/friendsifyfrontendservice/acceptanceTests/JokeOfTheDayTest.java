package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class JokeOfTheDayTest extends BaseLoggedInAcceptanceTest {

    @Test
    public void isJokeFilled() {
        String text = getDriver().findElement(By.id("joke-text")).getText();

        assertTrue(StringUtils.isNotBlank(text));
    }

    @Test
    public void findJokeOfTheDay() {
        boolean result = getDriver().findElement(By.id("joke-of-the-day")).isDisplayed();

        assertTrue(result);
    }
}
