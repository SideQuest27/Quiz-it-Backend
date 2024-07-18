package com.kahoot.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer qId;
    @ManyToOne
    @JoinColumn(name = "sessionId")
    private Session session;
    private String question;
    private Character correctAns;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question(Session session, String question, Character correctAns, List<Answer> answers) {
        this.session = session;
        this.question = question;
        this.correctAns = correctAns;
        this.answers = answers;
    }
}
