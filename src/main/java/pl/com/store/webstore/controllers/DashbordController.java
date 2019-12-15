package pl.com.store.webstore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashbordController {
    @GetMapping("/dashboard")
    public String dashboard () {
        return "/dashboard";

    }
}
