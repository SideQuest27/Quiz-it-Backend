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
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sessionId;
    private String hostName;
    private String topic;
    private Integer seconds;
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private List<Question> questions;

    public Session(String hostName,String topic,Integer seconds, List<Question> questions)
    {
        this.hostName=hostName;
        this.topic=topic;
        this.seconds = seconds;
        this.questions = questions;
        for (Question question : questions)
        {
            question.setSession(this);
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question);
            }
        }
    }
}
