package org.example.recipie.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index"})
    public String index() {
        System.out.println("request");
        System.out.println("request");
        return "index";
    }
}
