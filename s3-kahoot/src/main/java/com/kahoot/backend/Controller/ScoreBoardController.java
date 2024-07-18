package com.kahoot.backend.Controller;

import com.kahoot.backend.DTO.ScoreDTO;
import com.kahoot.backend.Entity.ScoreBoard;
import com.kahoot.backend.Service.ScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kahoot")
public class ScoreBoardController
{
    @Autowired
    ScoreBoardService scoreBoardService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestParam String userName,@RequestParam Integer sessionId){
        Integer id = scoreBoardService.registerUser(userName,sessionId);
        if (id!=null){
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>("This username already exists!",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/sendResponse")
    public ResponseEntity<ScoreDTO> processResponse(@RequestParam Integer userId, @RequestParam Integer qId, @RequestParam Character response,@RequestParam Integer ms){
        ScoreDTO score = scoreBoardService.processResponse(userId,qId,response,ms);
        if (score.getScore()!=null)
        {
            return new ResponseEntity<>(score,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/scoreBoardRanking")
    public List<ScoreBoard> scoreBoardRanking(@RequestParam Integer sessionId){
        return scoreBoardService.scoreBoardRanking(sessionId);
    }
}
