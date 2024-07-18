package com.kahoot.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private Integer score;
    private Integer totalScore;
    private Boolean result;
}
