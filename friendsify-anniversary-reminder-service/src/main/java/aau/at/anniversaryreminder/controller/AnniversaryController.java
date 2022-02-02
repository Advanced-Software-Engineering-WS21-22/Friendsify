package aau.at.anniversaryreminder.controller;

import aau.at.anniversaryreminder.service.AnniversaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/anniversary")
public class AnniversaryController {

    @Autowired
    AnniversaryService anniversaryService;

    @GetMapping(value = "/reminder", params = {"email_initiator", "email_friend"})
    public String getAnniversaryInfo(@RequestParam(value = "email_initiator") String emailInitiator, @RequestParam(value = "email_friend") String emailFriend) {
        String response = "";
        if (anniversaryService.isTodayAnniversary(emailInitiator, emailFriend)) {
            response = anniversaryService.getAnniversaryReminder(emailInitiator, emailFriend);
        } else {
            response = "Days until anniversary: " + anniversaryService.daysUntilAnniversary(emailInitiator, emailFriend);
        }
        return response;
    }

}
