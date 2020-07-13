package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private Integer credentialid;
    private String url;
    private String username;
    private String key;
    private String encryptedPassword;
    private String unencryptedPassword;
    private Integer userid;

    public Credential(String url, String username, String key, String encryptedpassword, Integer userid) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.encryptedPassword = encryptedpassword;
        this.userid = userid;
    }

    public Credential(Integer credentialid, String url, String username, String key, String password, Integer userid) {
        this.credentialid = credentialid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.encryptedPassword = password;
        this.userid = userid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setEncryptedPassword(String password) {
        this.encryptedPassword = password;
    }

    public void setUserid(Integer userid) { this.userid = userid; }

    public String getUrl() {
        return this.url;
    }

    public String getUsername() {
        return this.username;
    }

    public String getKey() {
        return this.key;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public Integer getUserid() { return this.userid; }

    public String getUnencryptedPassword() {
        return this.unencryptedPassword;
    }

    public void setUnencryptedPassword(String password) {
        this.unencryptedPassword = password;
    }

    public Integer getCredentialid() {
        return this.credentialid;
    }

}
