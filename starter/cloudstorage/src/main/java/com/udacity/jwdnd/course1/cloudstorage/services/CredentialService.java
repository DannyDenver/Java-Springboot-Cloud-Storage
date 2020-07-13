package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final UserMapper userMapper;
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(UserMapper userMapper, EncryptionService encryptionService, CredentialMapper credentialMapper) {

        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    public void addCredential(Authentication authentication, CredentialForm credentialForm) {
        User user = userMapper.getUser(authentication.getName());

        String key = encryptionService.getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credentialForm.getPassword(), key);
        Credential credential = new Credential(credentialForm.getUrl(), credentialForm.getUsername(), key, encryptedPassword, user.getUserId());

        credentialMapper.insert(credential);
    }

    public List<Credential> getCredentials(Authentication authentication) {
        User user = userMapper.getUser(authentication.getName());

        return credentialMapper.getCredentials(user.getUserId());
    }



}
