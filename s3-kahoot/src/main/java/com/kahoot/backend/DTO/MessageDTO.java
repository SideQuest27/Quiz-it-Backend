package com.kahoot.backend.DTO;

import com.kahoot.backend.Enum.Access;
import com.kahoot.backend.Enum.InfoType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageDTO
{
    private Access access;
    private InfoType infoType;
    private Integer session;
    private String content;
}
