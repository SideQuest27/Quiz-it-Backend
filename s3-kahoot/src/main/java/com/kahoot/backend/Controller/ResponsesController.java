package com.kahoot.backend.Controller;

import com.kahoot.backend.DTO.ResultStatsDTO;
import com.kahoot.backend.Service.ResponsesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/kahoot")
public class ResponsesController {

    @Autowired
    ResponsesService responseService;

    @GetMapping("/questionResults")
    public List<ResultStatsDTO> questionResults(@RequestParam Integer qId)
    {
        return responseService.questionResults(qId);
    }
}