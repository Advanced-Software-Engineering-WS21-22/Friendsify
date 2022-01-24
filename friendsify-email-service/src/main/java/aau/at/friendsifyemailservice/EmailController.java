package aau.at.friendsifyemailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/email")
public class EmailController {

    @Autowired
    public final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping()
    public String showEmailsPage() {
        return "emails";
    }

    @PostMapping()
    public String createMail(@RequestBody Email email) {
        emailService.sendEmail(email.getTo(), email.getFrom(),
                email.getSubject(), email.getText());

        return email.toString();
    }
}
