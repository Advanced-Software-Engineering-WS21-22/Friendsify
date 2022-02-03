package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.dataSamples.FriendsRepository;
import aau.at.friendsifyfrontendservice.inputs.FriendsInput;
import aau.at.friendsifyfrontendservice.inputs.PersonInput;
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

import java.util.ArrayList;
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
    private RecommendationService recommendationService;

    private Friends[] friendsList_active;
    private Friends[] friendsList_passive;
    private Person[] selectable_persons;
    private Person[] allPersons;

    @GetMapping
    public String friends(Model model) throws HttpServerErrorException{
        friendsToPersonService.loadPersons();
        model.addAttribute("module", "friends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.friendsList_active = this.friendsService.getFriendsByInitiator(currentUser.getPerson().getEmail());
        this.friendsList_passive = this.friendsService.getFriendsByReceiver(currentUser.getPerson().getEmail());
        this.allPersons = this.personService.getPersons();
        System.out.println(this.friendsList_passive[0].getEmail_p_friend()+ " " + this.friendsList_passive[0].is_timed_out());

        model.addAttribute("friendsList_active", this.friendsList_active);
        model.addAttribute("friendsList_passive", this.friendsList_passive);
        return "friends";
    }

    @GetMapping("/new")
    public String newFriend(Model model) throws HttpServerErrorException {
        model.addAttribute("module", "newFriends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.findFriendsService.loadData(currentUser.getPerson().getEmail());
        this.selectable_persons = this.findFriendsService.findSelectablePersons(currentUser.getPerson().getEmail());
        Recommendation recommendation = this.recommendationService.getRecommendationForPerson(currentUser.getPerson().getId_p());

        model.addAttribute("selectable_persons", this.selectable_persons);
        model.addAttribute("recommendation_age", recommendation.getRecommendedByAge().getFirst_name() +" "+recommendation.getRecommendedByAge().getLast_name());
        model.addAttribute("recommendation_common_friends", recommendation.getRecommendedByCommonFriends().getFirst_name()+" "+recommendation.getRecommendedByCommonFriends().getLast_name());

        FriendsInput friendsInput = new FriendsInput();
        friendsInput.setEmail_p_initiator(currentUser.getPerson().getEmail());
        friendsInput.set_timed_out(false);

        model.addAttribute("friendsForm", friendsInput);
        return "addFriend";
    }

    @PostMapping("/new")
    public RedirectView addFriend(@ModelAttribute(value="friendsForm") FriendsInput friendsForm, Model model) throws HttpServerErrorException {
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
    public RedirectView sendMail(@ModelAttribute(value="mail") Email email) throws HttpServerErrorException {
        this.emailService.sendEmail(email);
        return new RedirectView("/friendsify/friends");
    }

    @PostMapping("/sendJoke/{person_mail}/{friend_mail}")
    public RedirectView sendJoke(@PathVariable("person_mail") String person_mail, @PathVariable("friend_mail") String friend_mail) throws HttpServerErrorException {
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

    @PostMapping("/timeout/{id_fs}")
    public RedirectView setTimeOut(@PathVariable("id_fs") Long id_fs) throws HttpServerErrorException {
        Friends friend = Arrays.stream(this.friendsList_passive).filter(f -> f.getId_friend() == id_fs).toArray(Friends[]::new)[0];
        System.out.println("DEBUG TIMEOUT" + friend.getId_friend() + " " + friend.getEmail_p_friend() + " " + friend.getEmail_p_initiator() + " " + friend.is_timed_out() + " " + friend.getFs_start_date());
        //friend.set_timed_out(!friend.is_timed_out());
        this.friendsService.updateFriends(friend);
        System.out.println("Redirect View");
        return new RedirectView("/friendsify/friends");
    }
}
