package aau.at.friendsifyfrontendservice.contollerTests;
import aau.at.friendsifyfrontendservice.controllers.LoginController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc
public class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsServiceMock;

    @Test
    public void testGetLogin() throws Exception {
        this.mockMvc.perform(get("/login")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostLogin() throws Exception {
        this.mockMvc.perform(post("/login")
                        .contentType("application/json"))
                .andExpect(redirectedUrl("/login?error"));
    }

}
