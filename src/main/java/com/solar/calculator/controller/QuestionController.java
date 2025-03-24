package com.solar.calculator.controller;


import com.solar.calculator.dto.QuestionData;
import com.solar.calculator.service.QuestionService;
import com.solar.calculator.utils.DeferredResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("/api/v1/question")
@CrossOrigin("*")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add-question")
    public DeferredResult<String> addQuestion(@RequestBody QuestionData questionData){
        return DeferredResultUtil.executeAsync(()->{
            try {
               return questionService.addQuestion(questionData);
            }catch (Exception e){
                return "Error occured : "+e.getMessage();
            }

        });
    }
}
