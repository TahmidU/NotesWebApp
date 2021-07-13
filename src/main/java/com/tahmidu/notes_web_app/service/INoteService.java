package com.tahmidu.notes_web_app.service;

import com.tahmidu.notes_web_app.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface INoteService {

    Page<Note> getAllNotes(Pageable pageable);
    Page<Note> getNotesByQuery(Pageable pageable, String search);
    Note getNoteById(Long noteId);
    Note updateNote(Note updatedNote);
    void deleteNoteById(Long noteId);
    boolean existsById(Long noteId);

}
