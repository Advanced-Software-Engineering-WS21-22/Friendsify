package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.dataSamples.FriendsRepository;
import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.inputs.PersonInput;
import aau.at.friendsifyfrontendservice.models.Email;
import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.EmailService;
import aau.at.friendsifyfrontendservice.services.FindFriendsService;
import aau.at.friendsifyfrontendservice.services.FriendsService;
import aau.at.friendsifyfrontendservice.services.FriendsToPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsRepository friendsRepository = FriendsRepository.getFriendsRepository();

    @Autowired
    private FriendsToPersonService friendsToPersonService;

    @Autowired
    private FindFriendsService findFriendsService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private EmailService emailService;

    private ArrayList<Friends> friendsList_active;
    private ArrayList<Friends> friendsList_passive;
    private Person[] selectable_persons;

    @GetMapping
    public String friends(Model model) {
        friendsToPersonService.loadPersons();
        model.addAttribute("module", "friends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.friendsList_active = this.friendsRepository.getFriendsActive(currentUser.getPerson().getEmail());
        this.friendsList_passive = this.friendsRepository.getFriendsPassive(currentUser.getPerson().getEmail());

        model.addAttribute("friendsList_active", this.friendsList_active);
        model.addAttribute("friendsList_passive", this.friendsList_passive);
        return "friends";
    }

    @GetMapping("/new")
    public String newFriend(Model model) {
        model.addAttribute("module", "newFriends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.findFriendsService.loadData(currentUser.getPerson().getEmail());
        this.selectable_persons = this.findFriendsService.findSelectablePersons(currentUser.getPerson().getEmail());

        model.addAttribute("selectable_persons", this.selectable_persons);

        FriendsInput friendsInput = new FriendsInput();
        friendsInput.setEmail_p_initiator(currentUser.getPerson().getEmail());
        friendsInput.set_timed_out(false);

        model.addAttribute("friendsForm", friendsInput);
        return "addFriend";
    }

    @PostMapping("/new")
    public RedirectView addFriend(@ModelAttribute(value="friendsForm") FriendsInput friendsForm, Model model) {
        System.out.println(friendsForm.getEmail_p_initiator());
        System.out.println(friendsForm.getEmail_p_friend());

        this.friendsService.addFriends(friendsForm);

        return new RedirectView("./");
    }


    @GetMapping("/sendMail/{email_from}/{email_to}")
    public String sendMailForm(@PathVariable("email_from") String email_from, @PathVariable("email_to") String email_to, Model model) {
        Email email = new Email();
        email.setTo(email_to);
        email.setFrom(email_from);

        model.addAttribute("mail", email);

        return "mail";
    }

    @PostMapping("/sendMail")
    public RedirectView sendMail(@ModelAttribute(value="mail") Email email, Model model) {
        this.emailService.sendEmail(email);
        return new RedirectView("./");
    }
}
