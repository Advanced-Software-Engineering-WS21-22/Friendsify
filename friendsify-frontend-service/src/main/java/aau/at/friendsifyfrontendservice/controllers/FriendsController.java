package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.authentication.FriendsifyUser;
import aau.at.friendsifyfrontendservice.dataSamples.FriendsRepository;
import aau.at.friendsifyfrontendservice.models.Friends;
import aau.at.friendsifyfrontendservice.services.FriendsToPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsRepository friendsRepository = FriendsRepository.getFriendsRepository();

    @Autowired
    private FriendsToPersonService friendsToPersonService;

    private ArrayList<Friends> friendsList_active;
    private ArrayList<Friends> friendsList_passive;

    @GetMapping
    public String friends(Model model) {
        model.addAttribute("module", "friends");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.friendsList_active = this.friendsRepository.getFriendsActive(currentUser.getPerson().getId_p());
        this.friendsList_passive = this.friendsRepository.getFriendsPassive(currentUser.getPerson().getId_p());

        model.addAttribute("friendsList_active", this.friendsList_active);
        model.addAttribute("friendsList_passive", this.friendsList_passive);
        return "friends";
    }

    @GetMapping("/new")
    public String newFriend(Model model) {
        model.addAttribute("module", "newFriends");
        /*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        FriendsifyUser currentUser = (FriendsifyUser) auth.getPrincipal();

        this.friendsList_active = this.friendsRepository.getFriendsActive(currentUser.getPerson().getId_p());
        this.friendsList_passive = this.friendsRepository.getFriendsPassive(currentUser.getPerson().getId_p());

        model.addAttribute("friendsList_active", this.friendsList_active);
        model.addAttribute("friendsList_passive", this.friendsList_passive);
        return "friends";
        */
        return "addFriend";
    }
}
