package aau.at.friendsifyfrontendservice.controllers;


import aau.at.friendsifyfrontendservice.dataSamples.PersonRepository;
import aau.at.friendsifyfrontendservice.exceptions.PasswordMatchException;
import aau.at.friendsifyfrontendservice.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Controller
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    PersonRepository personRepository;

    private ArrayList<Person> personArrayList;

    @ModelAttribute("module")
    String module() {
        return "persons";
    }

    @GetMapping
    public String persons(Model model) {
        this.personArrayList = this.personRepository.getPersons();
        model.addAttribute("personList", this.personArrayList);

        Person personForm = new Person();
        model.addAttribute("personForm", personForm);
        return "persons";
    }

    @GetMapping("{id_p}")
    public String person(Model model, @PathVariable("id_p") Long id_p) {
        Integer key = (int) (long) id_p;

        Person person = personArrayList.get(key);

        int age = Period.between(person.getBirthday(), LocalDate.now()).getYears();

        model.addAttribute("selectedPerson", person);
        model.addAttribute("personAge", age);
        return "person";
    }

    @PostMapping()
    public RedirectView addPerson (@ModelAttribute(value="personForm") Person personForm, Model model) throws PasswordMatchException {

        if(!personForm.getPassword().equals(personForm.getRepeat_password())) {
            System.out.println("Passwords: " + personForm.getPassword() + " " + personForm.getRepeat_password());
            throw new PasswordMatchException("Passwords don't match.");
        } else {
            personForm.setId_p(new Long(this.personArrayList.size()));
            this.personArrayList.add(personForm);
        }

        return new RedirectView("./persons");
    }


}
