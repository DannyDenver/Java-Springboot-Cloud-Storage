package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public File(String filename, String contenttype, String filesize, Integer userid, byte[] filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public String getFilename() {
        return this.filename;
    }

    public Integer getFileId() {
        return this.fileId;
    }

    public String getContenttype() {
        return this.contenttype;
    }

    public String getFilesize() {
        return this.filesize;
    }

    public byte[] getFiledata() {
        return this.filedata;
    }
}
