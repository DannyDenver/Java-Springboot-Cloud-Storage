package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    private final FileMapper fileMapper;
    private final UserMapper userMapper;
    private final FileService fileService;

    public FileController(FileMapper fileMapper, UserMapper userMapper, FileService fileService) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
        this.fileService = fileService;
    };

    @PostMapping("/file-upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) {
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
