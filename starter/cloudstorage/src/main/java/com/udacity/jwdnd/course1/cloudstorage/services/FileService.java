package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {
    private final FileMapper fileMapper;
    private final UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public ResponseEntity<ByteArrayResource> getFile(Integer fileId) {
        File file = fileMapper.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContenttype()))
                .body(new ByteArrayResource(file.getFiledata()));
    }

    public void uploadFile(MultipartFile fileUpload, Authentication authentication) throws IOException {
        InputStream fis = fileUpload.getInputStream();
        User user = userMapper.getUser(authentication.getName());

        File file = new File(StringUtils.cleanPath(fileUpload.getOriginalFilename()),
                fileUpload.getContentType(),
                Long.toString(fileUpload.getSize()),
                user.getUserId(),
                fis.readAllBytes());

        fileMapper.insert(file);
    }

    public void deleteFile(Integer fileId) {
        fileMapper.deleteFile(fileId);
    }
}
