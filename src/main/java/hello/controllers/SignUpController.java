package hello.controllers;

import hello.db.datasets.User;
import hello.db.dbhelper.SignUpResult;
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
public class SignUpController {

    @Autowired
    private UserManagementService userMngService;

    @GetMapping("/signup")
    public String signUp() {
        return "sign_up";
    }

    @PostMapping("/signup")
    public @ResponseBody
    String signUser(@RequestParam(name = "username") String username,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "email") String email,
                    HttpServletRequest request,
                    Model model) {

        SignUpResult result = userMngService.signUpUser(new User(username, password, email));

        return result.toString();

    }
}
