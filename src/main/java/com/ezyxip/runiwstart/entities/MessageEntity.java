package com.ezyxip.runiwstart.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String author;
    String text;
    LocalDateTime date;

    public MessageEntity() {
    }

    public MessageEntity(Long id, String author, String text, LocalDateTime dateTime) {
        Id = id;
        this.author = author;
        this.text = text;
        this.date = dateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dateTime) {
        this.date = dateTime;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "Id=" + Id +
                ", author='" + author + '\'' +
                ", text='" + text + '\'' +
                ", dateTime=" + date +
                '}';
    }
}
