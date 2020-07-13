package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {
    private String noteTitle;
    private String noteDescription;

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String title) {
        this.noteTitle = title;
    }

    public String getNoteDescription() {
        return this.noteDescription;
    }

    public void setNoteDescription(String note) {
        this.noteDescription = note;
    }

}
