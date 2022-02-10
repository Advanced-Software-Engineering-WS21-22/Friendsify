package aau.at.friendsifyrecommendationsservice.controllers;

import aau.at.friendsifyrecommendationsservice.exceptions.PersonNotFoundException;
import aau.at.friendsifyrecommendationsservice.models.Recommendation;
import aau.at.friendsifyrecommendationsservice.recommenderlogic.RecommenderCoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    RecommenderCoordinator recommenderCoordinator;

    @GetMapping(value = "/{id_p}")
    public ResponseEntity<Recommendation> getRecommendation(@PathVariable(name = "id_p", required = true) Long id_p) throws PersonNotFoundException {
        Recommendation response = recommenderCoordinator.findRecommendation(id_p);
        return ResponseEntity.ok().body(response);
    }

}
