package aau.at.friendsifyemailservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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


    @PostMapping()
    public Email createEmail(@RequestBody Email email){
        return emailDAO.save(email);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Email> updateEmail(@PathVariable("id") Long id, @RequestBody Email email){
//        Email originEmail = emailDAO.findById(id);
//        originEmail.updateFromDTO(email);
//        Email updatedEmail = emailDAO.save(originEmail);
//        return ResponseEntity.ok(updatedEmail);
//    }

//    @DeleteMapping("/{id}")
//    public Map<String, Boolean> deleteEmail(@PathVariable("id") Long id){
//        Email originEmail = emailDAO.findById(id);
//        emailDAO.delete(originEmail);
//        Map<String, Boolean>  map = new HashMap<>();
//        map.put("deleted", Boolean.TRUE);
//        return map;
//
//    }
}
