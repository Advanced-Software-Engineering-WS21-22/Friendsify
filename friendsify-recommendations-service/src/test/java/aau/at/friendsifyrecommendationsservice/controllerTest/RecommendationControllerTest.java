package aau.at.friendsifyrecommendationsservice.controllerTest;

import aau.at.friendsifyrecommendationsservice.TestSamples;
import aau.at.friendsifyrecommendationsservice.controllers.RecommendationController;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.RecommenderCoordinator;
import aau.at.friendsifyrecommendationsservice.services.FriendsService;
import aau.at.friendsifyrecommendationsservice.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RecommendationController.class)
@AutoConfigureMockMvc
public class RecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /*
    @Test
    public void testGetRecommendation() throws Exception {
        RecommenderCoordinator recommenderCoordinator = spy(new RecommenderCoordinator());
        when(recommenderCoordinator.findRecommendation(1L)).thenReturn(new Recommendation());

        this.mockMvc.perform(get("/recommendations/1")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    */

}
