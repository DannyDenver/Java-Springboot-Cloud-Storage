package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RequestMapping
public class FileUploadController {

    private final FileMapper fileMapper;

    public SignupController(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    };

    @PostMapping
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {
        InputStream fis = fileUpload.getInputStream();

        File file = new File(fileUpload.getName(), fileUpload.getContentType(), fileUpload.getSize(), 3, fis.readAllBytes());

        fileMapper.insert(file);

    }
}
