package com.tahmidu.notes_web_app.repository;

import com.tahmidu.notes_web_app.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@PropertySource("application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NoteRepositoryIT {

    @Autowired INoteRepository noteRepository;

    @BeforeEach
    public void init(){
        Assertions.assertNotNull(noteRepository);
    }

    @Test
    public void givenTitle_whenSearchNoteByTitle_thenReturnCorrectNote(){

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        Note expectedNote = notes.get(0);
        int expectedSize = 1;

        String searchTitle = expectedNote.getTitle();

        // When
        Page<Note> pagedNote = noteRepository.findNotesByQuery(PageRequest.of(0, 5), searchTitle.toLowerCase());

        // Then
        Assertions.assertEquals(expectedSize, pagedNote.getContent().size());

        boolean isFound = false;
        for (Note note : pagedNote.getContent()) {
            if (note.equals(expectedNote)) {
                isFound = true;
                break;
            }
        }

        if(!isFound)
            Assertions.fail();
    }

    @Test
    public void givenContent_whenSearchNoteByContent_thenReturnCorrectNote(){

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        Note expectedNote = notes.get(1);
        int expectedSize = 1;

        String searchContent = "apple";

        // When
        Page<Note> pagedNote = noteRepository.findNotesByQuery(PageRequest.of(0,5), searchContent.toLowerCase());

        // Then
        Assertions.assertEquals(expectedSize, pagedNote.getContent().size());

        boolean isFound = false;
        for(Note note : pagedNote.getContent()){
            if(note.equals(expectedNote)){
                isFound = true;
                break;
            }
        }

        if(!isFound)
            Assertions.fail();

    }

    private List<Note> generateNotes(){

        List<Note> notes = new ArrayList<>();

        notes.add(new Note(1L, "Monday Morning", "Had breakfast."));
        notes.add(new Note(2L, "Todo List", "Get apple from the shops."));

        return notes;
    }

}
