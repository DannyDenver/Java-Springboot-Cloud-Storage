package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @GetMapping
    public String home(Authentication authentication, Model model) {
        model.addAttribute("files", fileService.getFiles(authentication));
        model.addAttribute("notes", noteService.getNotes(authentication));
        model.addAttribute("credentials", credentialService.getCredentials(authentication));

        model.addAttribute("credentialForm", new CredentialForm());
        model.addAttribute("noteForm", new NoteForm());

        return "home";
    }

    @PostMapping
    public String postFile() {
        return "home";
    }


}
