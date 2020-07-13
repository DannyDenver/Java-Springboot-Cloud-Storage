package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private String credentialid;
    private String url;
    private String username;
    private String key;
    private String password;
    public Integer userid;

    public Credential(String url, String username, String key, String password, Integer userid) {
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return this.password;
    }

    public Integer getUserid() { return this.userid; }

}
