package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper notemapper;
    private final UserMapper userMapper;

    public NoteService(NoteMapper notemapper, UserMapper userMapper) {
        this.notemapper = notemapper;
        this.userMapper = userMapper;
    }

    public List<Note> getNotes(Authentication authentication)
    {
        User user = userMapper.getUser(authentication.getName());
        return notemapper.getNotes(user.getUserId());
    }

    public void saveNote(NoteForm noteForm, Authentication authentication) {
        User user = userMapper.getUser(authentication.getName());

        Note note = new Note(noteForm.getNoteTitle(), noteForm.getNoteDescription(), user.getUserId());

        notemapper.insert(note);
    }
}
