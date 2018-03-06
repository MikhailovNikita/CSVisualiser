package hello.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ParseController {

    @GetMapping("/parse")
    public String getParsePage(Model model) {
        return "csv_parse";
    }


    @PostMapping("/parse")
    public @ResponseBody
    String postParsePage(@RequestParam String csvData, Model model) {
        return csvData;
    }
}
