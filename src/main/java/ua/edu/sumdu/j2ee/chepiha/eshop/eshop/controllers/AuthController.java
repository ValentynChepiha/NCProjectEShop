package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signInGet(){
        log.info("AuthController signInGet start ...");
        return "pages/auth/sign-in";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model){
        log.info("AuthController signUpGet start ...");
        model.addAttribute("user", new User());
        return "pages/auth/sign-up";
    }

    @PostMapping("/signup")
    public String signUpPost(Model model, @Valid User user,  BindingResult bindingResult){
        log.info("AuthController signUpPost start ...");
        if(bindingResult.hasErrors()) {
            return "pages/auth/sign-up";
        }
        if(!userService.createUser(user)) {
            model.addAttribute("resultCreate", false);
            return "pages/auth/sign-up";
        }
        return "redirect:/signin";
    }

}
