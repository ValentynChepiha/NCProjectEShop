package ua.edu.sumdu.j2ee.chepiha.eshop.eshop.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/favicon.ico")
    @ResponseBody
    public void favicon() {}

    @GetMapping
    public String main(Model model) {
        logger.info("Main page rendering...");
        return "welcome";
    }

}