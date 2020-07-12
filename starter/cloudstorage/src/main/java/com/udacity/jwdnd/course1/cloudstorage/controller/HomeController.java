package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileMapper fileMapper;

    public HomeController(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("files", this.fileMapper.getFiles());

        return "home";
    }

    @PostMapping
    public String postFile() {
        return "home";
    }


}
