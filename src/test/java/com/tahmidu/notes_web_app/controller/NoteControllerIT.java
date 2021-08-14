package com.tahmidu.notes_web_app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tahmidu.notes_web_app.model.Note;
import com.tahmidu.notes_web_app.repository.INoteRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(locations = "classpath:application-test.properties")
public class NoteControllerIT {

    @Autowired private MockMvc mockMvc;

    @Autowired private INoteRepository noteRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp(){

        Assertions.assertNotNull(mockMvc);
        Assertions.assertNotNull(noteRepository);

    }

    @Test
    public void givenNote_whenGetNote_thenStatusOK() throws Exception {

        // Given
        List<Note> expectedNotes = generateNotes();
        noteRepository.saveAll(expectedNotes);

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/note").param("page", "0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        List<Note> actualNotes = objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.content"), new TypeReference<>() {});

        Assertions.assertEquals(expectedNotes, actualNotes);

    }

    @Test
    public void givenNote_whenGetNoteByTitleInSearch_thenStatusOK() throws Exception {

        // Given
        List<Note> expectedNotes = generateNotes();
        Note expectedNote = expectedNotes.get(0);
        noteRepository.saveAll(expectedNotes);

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/note")
                .param("page", "0").param("search", expectedNote.getTitle()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()", Matchers.is(1)))
                .andReturn();

        List<Note> actualNotes = objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$.content"), new TypeReference<>() {});
        Note actualNote = actualNotes.get(0);

        Assertions.assertEquals(expectedNote, actualNote);

    }

    @Test
    public void givenNoteId_whenGetNoteById_thenReturnCorrectNoteAndStatusOK() throws Exception{

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        Note note = notes.get(0);
        long expectedNoteId = note.getNoteId();

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/note/%s", expectedNoteId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        Note actualNote = objectMapper.convertValue(JsonPath.read(result.getResponse().getContentAsString(),
                "$"), new TypeReference<>() {});
        Long actualNoteId = actualNote.getNoteId();

        Assertions.assertEquals(expectedNoteId, (long) actualNoteId);

    }

    @Test
    public void givenInvalidNoteId_whenGetNoteById_thenStatusNotFound() throws Exception{

        // Given
        long invalidNoteId = 5;

        // When Then
        mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/note/%s", invalidNoteId)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenValidNote_whenAddNote_thenReturnNoteAndStatusOK() throws Exception {

        // Given
        Note note = new Note("Title", "Content");
        Note expectedNote = new Note(1L, "Title", "Content");

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedNote)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Note actualNote = objectMapper.readValue(result.getResponse().getContentAsString(), Note.class);
        Assertions.assertEquals(expectedNote, actualNote);
    }

    @Test
    public void givenAlreadyExistingNote_whenAddNote_thenReturnStatusConflict() throws Exception {

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        Note invalidNote = new Note(1L, "Test", "Test");

        // When Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidNote)))
                .andExpect(MockMvcResultMatchers.status().isConflict());

    }

    @Test
    public void givenNote_whenUpdateNote_thenReturnUpdatedNoteAndStatusOK() throws Exception{

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        Note expectedNote = notes.get(0);
        expectedNote.setTitle("Tuesday Morning");

        // When Then
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(expectedNote)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Note actualNote = objectMapper.readValue(result.getResponse().getContentAsString(), Note.class);

        Assertions.assertEquals(expectedNote, actualNote);

    }

    @Test
    public void givenInvalidNote_whenUpdateNonExistentNote_thenStatusNoteFound() throws Exception{

        // Given
        Note invalidNote = new Note(1L, "Title", "Content");

        // When Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidNote)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void givenNoteId_whenDeleteNote_thenStatusOK() throws Exception{

        // Given
        List<Note> notes = generateNotes();
        noteRepository.saveAll(notes);

        long noteId = notes.get(0).getNoteId();

        // When Then
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/note/%s", noteId)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void givenInvalidNoteId_whenDeleteInvalidNote_thenStatusNotFound() throws Exception{

        // Given
        long invalidNoteId = 5;

        // When Then
        mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/note/%s", invalidNoteId)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    private List<Note> generateNotes(){

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, "Monday Morning", "Had breakfast."));
        notes.add(new Note(2L, "Todo List", "Get apple from the shops."));
        return notes;
    }

}
