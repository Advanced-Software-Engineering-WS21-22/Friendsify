package aau.at.friendsifyfrontendservice.controllers;

import aau.at.friendsifyfrontendservice.exceptions.PasswordMatchException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(PasswordMatchException.class)
    public RedirectView passwordMatchError(PasswordMatchException exception) {
        System.out.println(exception.getMessage());
        return new RedirectView("./persons?pwmatcherror");
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public String internalServerError(HttpServerErrorException exception, Model model) {
        System.out.println("ErrorController: " + exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return "serverError";
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String clientError(HttpClientErrorException exception, Model model) {
        System.out.println("ErrorController: " + exception.getMessage());
        model.addAttribute("errorMessage", exception.getMessage());
        return "clientError";
    }
}
