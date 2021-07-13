package com.tahmidu.notes_web_app.service;

import com.tahmidu.notes_web_app.model.Note;
import com.tahmidu.notes_web_app.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class NoteService implements INoteService {

    @Autowired private INoteRepository noteRepository;

    @Override
    public Page<Note> getAllNotes(Pageable pageable) {
        return noteRepository.findAll(pageable);
    }

    @Override
    public Page<Note> getNotesByQuery(Pageable pageable, String search) {
        return noteRepository.getNotesByQuery(pageable, search);
    }

    @Override
    public Note getNoteById(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        return noteOptional.orElse(null);
    }

    @Override
    public Note updateNote(Note updatedNote) {
        return noteRepository.save(updatedNote);
    }

    @Override
    public void deleteNoteById(Long noteId) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public boolean existsById(Long noteId) {
        return noteRepository.existsById(noteId);
    }
}
