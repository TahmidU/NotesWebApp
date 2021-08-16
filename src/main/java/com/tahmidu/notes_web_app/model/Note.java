package com.tahmidu.notes_web_app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "note")
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    private Long noteId;

    @Column(name = "title", nullable = false) private String title;
    @Column(name = "content") private String content;

    public Note(){}

    public Note(Long noteId, String title, String content) {
        this.noteId = noteId;
        this.title = title;
        this.content = content;
    }

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
