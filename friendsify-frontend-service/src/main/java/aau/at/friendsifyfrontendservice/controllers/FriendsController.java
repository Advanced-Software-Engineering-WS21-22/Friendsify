package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.models.Email;
import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.models.Recommendation;
import aau.at.friendsifyfrontendservice.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsToPersonService friendsToPersonService;

    @Autowired
    private FindFriendsService findFriendsService;

    @Autowired
    private FriendsService friendsService;

    @Autowired
    private PersonService personService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JokeService jokeService;

    @Autowired
    private AnniversaryService anniversaryService;

    @Autowired
    private RecommendationService recommendationService;

    private Friends[] friendsListActive;
    private Friends[] friendsListPassive;
    private Person[] selectablePersons;
    private Person[] allPersons;

    @GetMapping
    public String friends(Model model) throws HttpServerErrorException{
        friendsToPersonService.loadPersons();
        model.addAttribute("module", "friends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        try {
            this.friendsListActive = this.friendsService.getFriendsByInitiator(currentUser.getPerson().getEmail());
        } catch (Exception e) {
            this.friendsListActive = new Friends[0];
        }

        try{
            this.friendsListPassive = this.friendsService.getFriendsByReceiver(currentUser.getPerson().getEmail());
        } catch (Exception e) {
            this.friendsListPassive = new Friends[0];
        }

        this.allPersons = this.personService.getPersons();

        model.addAttribute("friendsListActive", this.friendsListActive);
        model.addAttribute("friendsListPassive", this.friendsListPassive);
        return "friends";
    }

    @GetMapping("/new")
    public String newFriend(Model model) throws HttpServerErrorException {
        model.addAttribute("module", "newFriends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.findFriendsService.loadData(currentUser.getPerson().getEmail());
        this.selectablePersons = this.findFriendsService.findSelectablePersons(currentUser.getPerson().getEmail());
        Recommendation recommendation = this.recommendationService.getRecommendationForPerson(currentUser.getPerson().getId_p());

        model.addAttribute("selectable_persons", this.selectablePersons);
        model.addAttribute("recommendation", recommendation);

        FriendsInput friendsInput = new FriendsInput();
        friendsInput.setEmail_p_initiator(currentUser.getPerson().getEmail());
        friendsInput.set_timed_out(false);

        model.addAttribute("friendsForm", friendsInput);
        return "addFriend";
    }

    @PostMapping("/new")
    public RedirectView addFriend(@ModelAttribute(value="friendsForm") FriendsInput friendsForm, Model model) throws HttpServerErrorException {
        this.friendsService.addFriends(friendsForm);
        return new RedirectView("/friendsify/friends");
    }


    @GetMapping("/sendMail/{email_from}/{email_to}")
    public String sendMailForm(@PathVariable("email_from") String email_from, @PathVariable("email_to") String email_to, Model model) throws HttpServerErrorException {
        Email email = new Email();
        email.setTo(email_to);
        email.setFrom(email_from);
        model.addAttribute("mail", email);
        return "mail";
    }

    @PostMapping("/sendMail")
    public RedirectView sendMail(@ModelAttribute(value="mail") Email email) throws HttpServerErrorException {
        this.emailService.sendEmail(email);
        return new RedirectView("/friendsify/friends");
    }

    @PostMapping("/sendJoke/{person_mail}/{friend_mail}")
    public RedirectView sendJoke(@PathVariable("person_mail") String person_mail, @PathVariable("friend_mail") String friend_mail) throws HttpServerErrorException {
        this.allPersons = this.personService.getPersons();
        Long id_p = Arrays.stream(this.allPersons).filter(p->p.getEmail().equals(person_mail)).toArray(Person[]::new)[0].getId_p();
        Long id_f = Arrays.stream(this.allPersons).filter(p->p.getEmail().equals(friend_mail)).toArray(Person[]::new)[0].getId_p();
        this.jokeService.sendJokeToFriend(id_p, id_f);
        return new RedirectView("/friendsify/friends");
    }

    @PostMapping("/cancel/{id_fs}")
    public RedirectView cancelFriendship(@PathVariable("id_fs") Long id_fs) throws HttpServerErrorException {
        this.friendsService.cancelFriendship(id_fs);
        return new RedirectView("/friendsify/friends");
    }

    @GetMapping("{id_fs}")
    public String friendsDetails(Model model, @PathVariable("id_fs") Long id_fs) throws HttpServerErrorException {
        Friends friends = this.friendsService.getFriendsByID(id_fs);
        String anniversary = this.anniversaryService.getAnniversary(friends.getEmail_p_initiator(), friends.getEmail_p_friend());

        model.addAttribute("selectedFriends", friends);
        model.addAttribute("anniversary", anniversary);
        return "friend";
    }
}
