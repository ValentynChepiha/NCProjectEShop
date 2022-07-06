package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.UserService;

import javax.validation.Valid;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class.getName());

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signInGet(){
        logger.info("AuthController signInGet start ...");
        return "pages/auth/sign-in";
    }

    @GetMapping("/signup")
    public String signUpGet(Model model){
        logger.info("AuthController signUpGet start ...");
        model.addAttribute("user", new User());
        return "pages/auth/sign-up";
    }

    @PostMapping("/signup")
    public String signUpPost(Model model, @Valid User user,  BindingResult bindingResult){
        logger.info("AuthController signUpPost start ...");
        if(bindingResult.hasErrors()) {
            return "/pages/auth/sign-up";
        }
        userService.createUser(user);
        return "redirect:/signin";
    }

}
