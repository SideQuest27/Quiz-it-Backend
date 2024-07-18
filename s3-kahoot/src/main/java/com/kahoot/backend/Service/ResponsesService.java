package com.kahoot.backend.Service;

import com.kahoot.backend.DTO.ResultStatsDTO;
import com.kahoot.backend.Repository.ResponsesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResponsesService {

    @Autowired
    ResponsesRepository responsesRepository;
    public List<ResultStatsDTO> questionResults(Integer qId) {
        return responsesRepository.questionResults(qId);
    }
}
