package aau.at.friendsifyemailservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/emails")
public class EmailController {

    @Autowired
    private EmailDAO emailDAO;

    @GetMapping()
    public List<Email> listEmails(){return emailDAO.findAll();}

    @GetMapping("/{from}")
    public List<Email> listEmailsFrom(@PathVariable("from") String from){
        List<Email> emailList = new ArrayList<>();
        for(Email e: emailDAO.findAll()){
            if(e.getFrom().equals(from)){
                emailList.add(e);
            }
        }
        return emailList;
    }
    @GetMapping("/{to}")
    public List<Email> listEmailsTo(@PathVariable("to") String to){
        List<Email> emailList = new ArrayList<>();
        for(Email e: emailDAO.findAll()){
            if(e.getFrom().equals(to)){
                emailList.add(e);
            }
        }
        return emailList;
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Email> listEmails(@PathVariable("id") Long id){
//        Email e = emailDAO.findById(id);
//        return ResponseEntity.ok(e);
//    }
}
