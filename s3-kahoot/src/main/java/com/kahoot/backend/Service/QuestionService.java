package com.kahoot.backend.Service;

import com.kahoot.backend.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuestionService
{
    @Autowired
    QuestionRepository questionRepository;
    public List<Long> getQuestionIds(Integer sessionId)
    {
        return questionRepository.getQuestionIds(sessionId);
    }
}
