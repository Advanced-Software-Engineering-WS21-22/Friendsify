package aau.at.friendsifyfrontendservice.acceptanceTests;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.By;

@Disabled
public class JokeOfTheDayTest extends BaseAcceptanceTest {

    @Test
    public void isJokeFilled() {
        String text = getDriver().findElement(By.id("joke-text")).getText();

        assertTrue(StringUtils.isNotBlank(text));
    }

    @Test
    public void findJokeOfTheDay() {
        assertTrue(getDriver().findElement(By.id("joke-of-the-day")).isDisplayed());
    }
}
