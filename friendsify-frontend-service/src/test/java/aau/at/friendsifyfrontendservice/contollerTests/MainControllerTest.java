package aau.at.friendsifyfrontendservice.contollerTests;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.controllers.MainController;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.models.WeatherResult;
import aau.at.friendsifyfrontendservice.services.PersonService;
import aau.at.friendsifyfrontendservice.services.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MainController.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsServiceMock;

    @MockBean
    private WeatherService weatherServiceMock;

    @Mock
    Authentication authentication;

    @Test
    public void testPersonsRequest() throws Exception {
        List authorities = new ArrayList();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        when(weatherServiceMock.getWeatherByLocation("Klagenfurt")).thenReturn(new WeatherResult());

        authentication = mock(Authentication.class);
        Person person = new Person(0L, "Max", "Mustermann", "max@mustermann.de", LocalDate.now(), "Q1234", "Klagenfurt", "password_hash");

        this.mockMvc.perform(get("/home")
                        .with(user(new FriendsifyUser(person, true, false, false, false, authorities)))
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
