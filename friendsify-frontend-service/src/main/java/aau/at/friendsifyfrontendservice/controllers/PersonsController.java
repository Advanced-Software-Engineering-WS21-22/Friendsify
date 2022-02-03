package aau.at.friendsifyfrontendservice.controllers;


import aau.at.friendsifyfrontendservice.dataSamples.PersonRepository;
import aau.at.friendsifyfrontendservice.exceptions.PasswordMatchException;
import aau.at.friendsifyfrontendservice.inputs.PersonInput;
import aau.at.friendsifyfrontendservice.models.Person;
import aau.at.friendsifyfrontendservice.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

@Controller
@RequestMapping("/persons")
public class PersonsController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    private ArrayList<Person> personArrayList;
    private Person[] allPersons;

    @ModelAttribute("module")
    String module() {
        return "persons";
    }

    @GetMapping
    public String persons(Model model) {

        this.allPersons = this.personService.getPersons();

        PersonInput personForm = new PersonInput();
        model.addAttribute("personForm", personForm);
        model.addAttribute("personList", this.allPersons);

        return "persons";
    }

    @GetMapping("{id_p}")
    public String person(Model model, @PathVariable("id_p") Long id_p) {

        Person person = personService.getPersonById(id_p);

        int age = Period.between(person.getBirthday(), LocalDate.now()).getYears();

        model.addAttribute("selectedPerson", person);
        model.addAttribute("personAge", age);
        return "person";
    }

    @PostMapping()
    public RedirectView addPerson(@ModelAttribute(value="personForm") PersonInput personForm) throws PasswordMatchException, HttpServerErrorException, HttpClientErrorException {

        if(!personForm.getPassword().equals(personForm.getRepeat_password())) {
            throw new PasswordMatchException("Passwords don't match.");
        } else {
            this.personService.addPerson(Person.fromPersonInput(personForm));
        }

        return new RedirectView("./persons");
    }


}
