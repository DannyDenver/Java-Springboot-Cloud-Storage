package com.udacity.jwdnd.course1.cloudstorage.model;

import org.springframework.security.core.parameters.P;

public class Note {
    private Integer noteid;
    private String notetitle;
    private String notedescription;
    private Integer userid;

    public Note(String notetitle, String notedescription, Integer userid) {
        this.notetitle = notetitle;
        this.notedescription = notedescription;
        this.userid = userid;
    }

    public void setNotetitle(String title) {
        this.notetitle = title;
    }

    public String getNotetitle() {
        return this.notetitle;
    }

    public void setNotedescription(String notedescription) { this.notedescription = notedescription; }

    public String getNotedescription() {
        return this.notedescription;
    }

    public void setUserid(Integer userId) {
        this.userid = userId;
    }

    public Integer getUserId() {
        return this.userid;
    }
}