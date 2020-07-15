package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    };

    @PostMapping("/file-upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) {
        if(!fileService.isFilenameAvailable(fileUpload.getOriginalFilename())) {
            model.addAttribute("uploadError", "Duplicate filename. Filenames must be unique.");
            return "result";
        }

        try {
            fileService.uploadFile(fileUpload, authentication);
            model.addAttribute("success", true);
        }catch (Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }

    @GetMapping("/file/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable(value="id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        return fileService.getFile(id);
    }

    @PostMapping("/file/{id}/delete")
    public String deleteFile(@PathVariable(value="id") Integer id, Model model) {
        try {
            fileService.deleteFile(id);
            model.addAttribute("success", true);
        }catch(Exception exception) {
            model.addAttribute("uploadError", exception.getMessage());
        }

        return "result";
    }
}
