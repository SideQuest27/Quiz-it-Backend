package com.kahoot.backend.Service;

import com.kahoot.backend.DTO.ScoreDTO;
import com.kahoot.backend.Entity.Question;
import com.kahoot.backend.Entity.Responses;
import com.kahoot.backend.Entity.ScoreBoard;
import com.kahoot.backend.Entity.Session;
import com.kahoot.backend.Repository.QuestionRepository;
import com.kahoot.backend.Repository.ResponsesRepository;
import com.kahoot.backend.Repository.ScoreBoardRepository;
import com.kahoot.backend.Repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ScoreBoardService {
        @Autowired
        ScoreBoardRepository scoreBoardRepository;
        @Autowired
        QuestionRepository questionRepository;
        @Autowired
        SessionRepository sessionRepository;
        @Autowired
        ResponsesRepository responsesRepository;
        public Integer registerUser(String userName, Integer sessionId)
        {
            Integer result = null;
            if (scoreBoardRepository.checkForSameUserName(userName,sessionId)==0)
            {
                Session s = sessionRepository.findById(sessionId).orElseThrow(()-> new IllegalStateException("Session Does Not Exist!"));
                result = scoreBoardRepository.save(new ScoreBoard(userName,s)).getUserId();
            }
            return result;
        }
        @Transactional
        public ScoreDTO processResponse(Integer userId, Integer qId, Character response, Integer ms)
        {
            Integer score = 0;
            Double milliSec = Double.valueOf(ms);
            Question question = questionRepository.findById(qId).orElseThrow(()->new IllegalStateException("Question Does Not Exist!"));
            Double maxSeconds= (double) (question.getSession().getSeconds()*1000);
            Boolean trueOrFalse = question.getCorrectAns().equals(response) && milliSec>100 && milliSec <= maxSeconds;
            Integer totalScore = scoreBoardRepository.getTotalScore(userId);
            if (trueOrFalse)
            {
                Integer calcResult = Math.toIntExact(Math.round(((maxSeconds - milliSec) / (maxSeconds - 100)) * 100));
                score += calcResult;
            }
            else {
                score+=0;
            }
            responsesRepository.save(new Responses(response,question));
            scoreBoardRepository.updateScore(userId,score);
            return new ScoreDTO(score,totalScore,trueOrFalse);
        }

    public List<ScoreBoard> scoreBoardRanking(Integer sessionId)
    {
        return scoreBoardRepository.scoreBoardRanking(sessionId);
    }
}
