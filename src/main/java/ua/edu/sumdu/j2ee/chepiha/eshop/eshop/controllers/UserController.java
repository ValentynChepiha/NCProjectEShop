package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.UserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.UserRoleRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.UserRole;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.ValidateService;

import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping("/users")
    public String users(Model model){
        logger.info("Info about all users rendering...");
        List<User> users = userRepository.getAll();
        model.addAttribute("users", users);
        return "pages/user/all";
    }

    @GetMapping("/users/add")
    public String usersAddGet(Model model){
        logger.info("Page create new user");
        List<UserRole> roles = userRoleRepository.getAll();
        model.addAttribute("roles", roles);
        return "pages/user/add";
    }

    @PostMapping("/users/add")
    public String usersAddPost(@RequestParam String userLogin, @RequestParam String userPass,
                               @RequestParam long userRole, Model model){
        logger.info("Page saving new user");
        User user = new User();
        user.setLogin(userLogin);
        user.setPassword(userPass);
        user.setAuthority( userRoleRepository.getOneOnlyAuthority(userRole) );
        if(user.validate()){
            userRepository.create(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String usersEditGet(@PathVariable(value = "id") long id, Model model){
        logger.info("Page edit user");
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        List<UserRole> roles = userRoleRepository.getAll();
        model.addAttribute("roles", roles);
        return "pages/user/edit";
    }

    @PostMapping("/users/edit")
    public String usersEditPost(@RequestParam long userId, @RequestParam String userPass,
                                @RequestParam long userRole, Model model){

        logger.info("Page updating user");
        User user = userRepository.getOne(userId);

        user.setAuthority( userRoleRepository.getOneOnlyAuthority(userRole) );

        if(ValidateService.validateString(userPass)){
            user.setPassword(userPass);
            userRepository.update(user);
        } else {
            userRepository.updateRole(user);
        }

        return "redirect:/users";
    }

}
