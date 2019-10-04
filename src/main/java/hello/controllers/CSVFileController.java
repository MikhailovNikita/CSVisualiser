package hello.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CSVFileController {
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String provideUploadInfo() {
        return "csv_file_send";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file,
                                   Model model) {
        if (!file.isEmpty()) {
            try {
                //TODO: make an utility to do it
                String completeFile = new String(file.getBytes());
                List<List<String>> preparedCSV = new ArrayList<>();
                String[] rows = completeFile.split("\n");
                for (String row : rows) {
                    List<String> listRow = new ArrayList<>();
                    row = row.replaceAll("\\\\\", "");
                    String[] statements = row.split(",");
                    listRow.addAll(Arrays.asList(statements));
                    preparedCSV.add(listRow);
                }


                model.addAttribute("objects", preparedCSV);

                return "csvmodel";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
