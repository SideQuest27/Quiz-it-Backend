package com.kahoot.backend.EventListener;

import com.kahoot.backend.DTO.MessageDTO;
import com.kahoot.backend.Enum.Access;
import com.kahoot.backend.Enum.InfoType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
@AllArgsConstructor
public class PlayerLeftEventListener {

    private final SimpMessageSendingOperations messagingTemplate;
    @EventListener
    public void playerLeftEventListener(SessionDisconnectEvent event){
        StompHeaderAccessor header = StompHeaderAccessor.wrap(event.getMessage());
        String role = (String) header.getSessionAttributes().get("role");
        if (role.equals("HOST")){
            log.info("HOST LEFT!");
            MessageDTO message = MessageDTO
                    .builder()
                    .access(Access.PLAYER)
                    .infoType(InfoType.HOST_LEFT)
                    .build();
            messagingTemplate.convertAndSend((String) header.getSessionAttributes().get("destination"),message);
        }
        else if (role.equals("PLAYER")){
            log.info("PLYER "+ header.getSessionAttributes().get("username") +" LEFT!");
            MessageDTO message = MessageDTO
                    .builder()
                    .access(Access.HOST)
                    .infoType(InfoType.PLAYER_LEFT)
                    .content((String) header.getSessionAttributes().get("username"))
                    .build();
            messagingTemplate.convertAndSend((String) header.getSessionAttributes().get("destination"),message);
        }
    }
}
