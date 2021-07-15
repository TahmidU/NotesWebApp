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

    @Column(name = "title", nullable = false) private String Title;
    @Column(name = "content", nullable = false) private String content;

    public Note(){}

    public Note(Long noteId, String title, String content) {
        this.noteId = noteId;
        Title = title;
        this.content = content;
    }


}
