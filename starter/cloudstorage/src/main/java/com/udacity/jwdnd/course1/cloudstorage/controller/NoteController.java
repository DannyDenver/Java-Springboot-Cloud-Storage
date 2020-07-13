package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/addNote")
    public String postNote(Authentication authentication, NoteForm noteForm, Model model) {
        try {
            if(noteForm.getNoteid() != null) {
                noteService.updateNote(noteForm);
            }else {
                noteService.saveNote(noteForm, authentication);
            }

            model.addAttribute("success", true);
        }catch (Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteCredential(@PathVariable(value="id") Integer id, Model model) {
        try {
            noteService.deleteNote(id);
            model.addAttribute("success", true);
        }catch(Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }
}
