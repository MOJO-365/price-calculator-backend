package com.solar.calculator.service;

import com.solar.calculator.dto.QuestionData;
import com.solar.calculator.utils.QuestionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private QuestionUtil questionUtil;

    @Autowired
    public QuestionService(QuestionUtil questionUtil){
        this.questionUtil=questionUtil;
    }

    public String addQuestion(QuestionData questionData){
        return questionUtil.insertQuestion(questionData.getCompany(),questionData.getPhoneNumber(),
                questionData.getCustomerName(),questionData.getQuestion());
    }
}
