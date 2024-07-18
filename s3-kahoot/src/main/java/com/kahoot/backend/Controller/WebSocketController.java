package com.kahoot.backend.Controller;

import com.kahoot.backend.DTO.MessageDTO;
import com.kahoot.backend.Enum.InfoType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@AllArgsConstructor
public class WebSocketController
{
    private final SimpMessageSendingOperations messagingTemplate;
    @MessageMapping("/sendMessage")
    private void sendMessage(@Payload MessageDTO message){
        messagingTemplate.convertAndSend("/topic/"+message.getSession(),message);
    }
    @MessageMapping("/joinChannel")
    public void joinChannel(@Payload MessageDTO message, SimpMessageHeaderAccessor headerAccessor)
    {
        log.info("New Connection: "+message.getContent());
        if (message.getInfoType() == InfoType.HOST_CONNECTED)
        {
            Map<String, Object> sessionAttributes = new HashMap<>();
            sessionAttributes.put("role", "HOST");
            sessionAttributes.put("username", message.getContent());
            sessionAttributes.put("destination","/topic/"+message.getSession());
            headerAccessor.getSessionAttributes().putAll(sessionAttributes);
        }
        else if (message.getInfoType() == InfoType.PLAYER_CONNECTED)
        {
            Map<String, Object> sessionAttributes = new HashMap<>();
            sessionAttributes.put("role", "PLAYER");
            sessionAttributes.put("username", message.getContent());
            sessionAttributes.put("destination","/topic/"+message.getSession());
            headerAccessor.getSessionAttributes().putAll(sessionAttributes);
        }
        messagingTemplate.convertAndSend("/topic/"+message.getSession(),message);
    }
}