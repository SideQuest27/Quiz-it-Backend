package com.kahoot.backend.Controller;
import com.kahoot.backend.DTO.HostDataDTO;
import com.kahoot.backend.DTO.LeaderboardDTO;
import com.kahoot.backend.Entity.Session;
import com.kahoot.backend.Service.QuestionService;
import com.kahoot.backend.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kahoot")
public class SessionController
{
    @Autowired
    SessionService sessionService;
    @Autowired
    QuestionService questionService;

    @PostMapping("/createSession")
    public ResponseEntity<HostDataDTO> createSession(@RequestBody Session gameData)
    {
        Session s = new Session(gameData.getHostName(),gameData.getTopic(),gameData.getSeconds(),gameData.getQuestions());
        Integer sessionId= sessionService.createSession(s);
        HostDataDTO hostData = new HostDataDTO(sessionId,questionService.getQuestionIds(sessionId));
        return new ResponseEntity<>(hostData, HttpStatus.OK);
    }

    @GetMapping("/checkForSession")
    public ResponseEntity<Void> checkForSession(@RequestParam Integer sessionId){
        if (sessionService.checkForSession(sessionId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/leaderBoard")
    public Page<LeaderboardDTO> leaderboardSessions(@RequestParam Integer page, @RequestParam Integer size)
    {
        return sessionService.getAllSessions(page,size);
    }
}
