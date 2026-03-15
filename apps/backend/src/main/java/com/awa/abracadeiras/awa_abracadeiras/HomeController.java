package com.awa.abracadeiras.awa_abracadeiras;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/swagger-ui/index.html";
    }
}
