package com.kahoot.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ansId;
    private Character charId;
    @ManyToOne
    @JoinColumn(name = "qId", referencedColumnName = "qId")
    private Question question;
    private String ans;

    public Answer(Character charId, Question question, String ans) {
        this.charId = charId;
        this.question = question;
        this.ans = ans;
    }
}

