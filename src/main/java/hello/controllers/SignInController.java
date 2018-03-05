package hello.controllers;


import hello.db.datasets.User;
import hello.db.dbhelper.SignInResult;
import hello.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignInController {

    @Autowired
    private UserManagementService userMngService;

    @GetMapping("/signin")
    public String signUser(Model model) {
        return "sign_in";
    }

    @PostMapping("/signin")
    public @ResponseBody
    String signUser(@RequestParam(name = "username") String username,
                    @RequestParam(name = "password") String password,
                    HttpServletRequest request,
                    Model model) {

        SignInResult result = userMngService
                .signIn(new User(username, password, username + "@gmail.com"));


        return result.toString();
    }
}
