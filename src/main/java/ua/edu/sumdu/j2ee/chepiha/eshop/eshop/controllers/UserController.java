package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelUserRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.interfaces.ModelUserRoleRepository;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.User;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.entities.UserRole;
import ua.edu.sumdu.j2ee.chepiha.eshop.eshop.models.services.UserService;

import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final ModelUserRepository<User> userRepository;
    private final ModelUserRoleRepository<UserRole> userRoleRepository;
    private final UserService userService;

    @Autowired
    public UserController(ModelUserRepository<User> userRepository,
                          ModelUserRoleRepository<UserRole> userRoleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model){
        log.info("Info about all users rendering...");
        List<User> users = userRepository.getAll();
        model.addAttribute("users", users);
        return "pages/user/all";
    }
    
    @GetMapping("/users/add")
    public String usersAddGet(Model model){
        log.info("Page create new user");
        model.addAttribute("roles", userRoleRepository.getAll());
        return "pages/user/add";
    }

    @PostMapping("/users/add")
    public String usersAddPost(@RequestParam String userLogin, @RequestParam String userPass,
                               @RequestParam long userRole, Model model){
        log.info("Page saving new user");
        if(!userService.createUser(userLogin, userPass, userRole)){
            model.addAttribute("resultCreate", false);
            model.addAttribute("roles", userRoleRepository.getAll());
            return "pages/user/add";
        }
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String usersEditGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page edit user");
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        List<UserRole> roles = userRoleRepository.getAll();
        model.addAttribute("roles", roles);
        return "pages/user/edit";
    }

    @PostMapping("/users/edit")
    public String usersEditPost(@RequestParam long userId, @RequestParam String userPass,
                                @RequestParam long userRole, Model model){
        log.info("Page updating user");
        userService.updateUser(userId, userPass, userRole);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String usersDeleteGet(@PathVariable(value = "id") long id, Model model){
        log.info("Page delete user");
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        return "pages/user/delete";
    }

    @PostMapping("/users/delete")
    public String usersDeletePost(@RequestParam long userId, Model model){
        log.info("Page deleting user");
        userRepository.delete(userId);
        return "redirect:/users";
    }

}
