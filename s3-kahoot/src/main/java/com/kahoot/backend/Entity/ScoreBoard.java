package com.kahoot.backend.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"session"})
public class ScoreBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;
    private Integer score = 0;
    @ManyToOne
    @JoinColumn(name = "sessionId",referencedColumnName = "sessionId")
    private Session session;

    public ScoreBoard(String userName, Session session) {
        this.userName = userName;
        this.session = session;
    }
}
