package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class MainController {

    @RequestMapping("/favicon.ico")
    @ResponseBody
    public void favicon() {}

    @GetMapping
    public String main(Model model) {
        log.info("Main page rendering...");
        return "welcome";
    }

}