package com.solar.calculator.utils;

import com.solar.calculator.config.GlobalDatabase;
import io.swagger.v3.core.util.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionUtil {

    GlobalDatabase globalDatabase;
    private static String QUESTION_TABLE="question";

    @Autowired
    public QuestionUtil(GlobalDatabase globalDatabase){
        this.globalDatabase=globalDatabase;
    }

    public String insertQuestion(String company, String phoneNumber, String name, Json question){
        String sql="";

        return "";
    }
}
