package com.ipi.jva320.cotrollers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String GetHomePage(){
        return "HomePage";
    }

}
