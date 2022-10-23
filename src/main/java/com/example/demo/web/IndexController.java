package com.example.demo.web;

import com.example.demo.security.TwoFaUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        TwoFaUserDetails user = (TwoFaUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("is2faEnabled", user.getUseGoogle2Fa());
        return "index";
    }

}
