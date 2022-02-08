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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc
class LoginControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsServiceMock;

    @Test
    void testGetLogin() throws Exception {
        this.mockMvc.perform(get("/login")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void testPostLogin() throws Exception {
        this.mockMvc.perform(post("/login")
                        .with(csrf())
                        .contentType("application/json"))
                .andExpect(redirectedUrl("/login?error"));
    }

}
