package com.tahmidu.notes_web_app.controller;

import com.tahmidu.notes_web_app.model.Note;
import com.tahmidu.notes_web_app.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
@CrossOrigin("*")
public class NoteController {

    @Autowired private INoteService noteService;

    @GetMapping(value = {"", "/"})
    public Page<Note> getNotes(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "search", required = false) String search){

        final int pageSize = 5;

        if(search == null){
            return noteService.getAllNotes(PageRequest.of(page, pageSize));
        }else {
            return noteService.getNotesByQuery(PageRequest.of(page, pageSize), search.toLowerCase());
        }

    }

    @GetMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Note> getNoteById(@PathVariable(name = "id") Long noteId){

        if(!noteService.existsById(noteId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(noteService.getNoteById(noteId), HttpStatus.OK);
    }

    @PostMapping(value = {"","/"})
    public ResponseEntity<Note> addNote(@RequestBody Note note){

        if(note.getNoteId() != null && noteService.existsById(note.getNoteId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.OK);
    }

    @PutMapping(value = {"", "/"})
    public ResponseEntity<Note> updateNote(@RequestBody Note updatedNote){

        if(!noteService.existsById(updatedNote.getNoteId()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(noteService.updateNote(updatedNote), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{id}", "/{id}/"})
    public ResponseEntity<Note> deleteNoteById(@PathVariable(name = "id") Long noteId){

        if(!noteService.existsById(noteId))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        noteService.deleteNoteById(noteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
