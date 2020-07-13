package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialController {
    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/addCredential")
    public String addCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Authentication authentication, Model model) {
        try {
            if(credentialForm.getCredentialId() != null) {
                credentialService.updateCredential(credentialForm);
            }else {
                credentialService.addCredential(authentication, credentialForm);
            }

            model.addAttribute("success", true);
        }catch (Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }

//    @PostMapping("/credentials/{id}/edit")
//    public String addCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, @PathVariable(value="id") Integer id, Authentication authentication, Model model) {
//        try {
//            credentialService.updateCredential(credentialForm);
//            model.addAttribute("success", true);
//        }catch (Exception exception) {
//            model.addAttribute("uploadError", exception.getMessage());
//        }
//
//        return "result";
//    }

    @PostMapping("/credential/{id}/delete")
    public String deleteCredential(@PathVariable(value="id") Integer id, Model model) {
        try {
            credentialService.deleteCredential(id);
            model.addAttribute("success", true);
        }catch(Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }
}
