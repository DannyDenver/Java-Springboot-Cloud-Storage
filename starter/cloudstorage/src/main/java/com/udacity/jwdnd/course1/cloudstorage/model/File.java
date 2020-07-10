package com.udacity.jwdnd.course1.cloudstorage.model;

import java.sql.Blob;

public class File {
    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private Blob filedata;

    public File(String filename, String contenttype, String filesize, Integer userid, Blob filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }
}
