package aau.at.anniversaryreminder;

import aau.at.anniversaryreminder.controller.AnniversaryController;
import aau.at.anniversaryreminder.service.AnniversaryServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private AnniversaryServiceImpl anniversaryService;

    @InjectMocks
    private AnniversaryController anniversaryController;

    private String emailInitiator;
    private String emailFriend;

    @BeforeEach
    public void setup(){
        emailInitiator = "test@mail.com";
        emailFriend = "sample@mail.com";
    }

    @AfterEach
    public void tearDown(){
        emailInitiator = null;
        emailFriend = null;
    }

    @Test
    public void testTodayIsAnniversary(){
        String expectedResult = "Today is test and samples 5 year anniversary!";
        Mockito.when(anniversaryService.isTodayAnniversary(emailInitiator, emailFriend)).thenReturn(true);
        Mockito.when(anniversaryService.getAnniversaryReminder(emailInitiator, emailFriend)).thenReturn(expectedResult);
        String actualResult = anniversaryController.getAnniversaryInfo(emailInitiator, emailFriend);
        Assertions.assertEquals(expectedResult, actualResult);
        Mockito.verify(anniversaryService, Mockito.times(1)).isTodayAnniversary(emailInitiator, emailFriend);
        Mockito.verify(anniversaryService, Mockito.times(1)).getAnniversaryReminder(emailInitiator, emailFriend);
    }

    @Test
    public void testTodayIsNotAnniversary(){
        String expectedResult = "Days until anniversary: 200";
        Mockito.when(anniversaryService.isTodayAnniversary(emailInitiator, emailFriend)).thenReturn(false);
        Mockito.when(anniversaryService.daysUntilAnniversary(emailInitiator, emailFriend, LocalDate.now())).thenReturn(200);
        String actualResult = anniversaryController.getAnniversaryInfo(emailInitiator, emailFriend);
        Assertions.assertEquals(expectedResult, actualResult);
        Mockito.verify(anniversaryService, Mockito.times(1)).isTodayAnniversary(emailInitiator, emailFriend);
        Mockito.verify(anniversaryService, Mockito.times(1)).daysUntilAnniversary(emailInitiator, emailFriend, LocalDate.now());
    }

}
