package com.kahoot.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeaderboardDTO {
    private Integer sessionId;
    private String hostName;
    private String topic;
}
