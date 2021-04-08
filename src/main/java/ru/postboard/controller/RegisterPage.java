package ru.postboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.postboard.form.UserCredentials;
import ru.postboard.form.validator.UserCredentialsRegisterValidator;
import ru.postboard.security.Guest;
import ru.postboard.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class RegisterPage extends Page {
    private final UserService userService;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;

    public RegisterPage(UserService userService, UserCredentialsRegisterValidator userCredentialsRegisterValidator) {
        this.userService = userService;
        this.userCredentialsRegisterValidator = userCredentialsRegisterValidator;
    }

    @InitBinder("registerForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }

    @Guest
    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new UserCredentials());
        return "RegisterPage";
    }

    @Guest
    @PostMapping("/register")
    public String registerPost(@Valid @ModelAttribute("registerForm") UserCredentials registerForm,
                           BindingResult bindingResult,
                           HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "RegisterPage";
        }

        setUser(httpSession, userService.register(registerForm));
        putMessage(httpSession, "Congrats, you have been registered!");

        return "redirect:/";
    }
}
