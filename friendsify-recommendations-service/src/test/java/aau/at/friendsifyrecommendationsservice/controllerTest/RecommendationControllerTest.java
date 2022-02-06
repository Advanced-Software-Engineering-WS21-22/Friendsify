package aau.at.friendsifyrecommendationsservice.controllerTest;
import aau.at.friendsifyrecommendationsservice.controllers.RecommendationController;
import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.RecommenderCoordinator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecommendationController.class)
@AutoConfigureMockMvc
@Disabled("Disabled")
public class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommenderCoordinator recommenderCoordinator;


    @Test
    public void testGetRecommendation() throws Exception {
        when(recommenderCoordinator.findRecommendation(1L)).thenReturn(new Recommendation());

        this.mockMvc.perform(get("/recommendations/1")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetRecommendationError() throws Exception {
        when(recommenderCoordinator.findRecommendation(-1L)).thenThrow(new PersonNotFoundException("Person not found"));

        this.mockMvc.perform(get("/recommendations/-1")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

}
