package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.exceptions.PasswordMatchException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionHandlingController {

    /*
    @ExceptionHandler(PasswordMatchException.class)
    public RedirectView passwordMatchException(PasswordMatchException exception) {
        System.out.println(exception.getMessage());
        return new RedirectView("./persons?error");
    }
    */

    @ExceptionHandler(PasswordMatchException.class)
    public RedirectView passwordMatchError(PasswordMatchException exception, Model model) {
        System.out.println(exception.getMessage());
        return new RedirectView("./persons?pwmatcherror");

    }

}
