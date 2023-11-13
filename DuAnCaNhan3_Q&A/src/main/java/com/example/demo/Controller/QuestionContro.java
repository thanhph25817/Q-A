package com.example.demo.Controller;

import com.example.demo.Entities.Question;
import com.example.demo.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")

public class QuestionContro {
    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuest")
    public ResponseEntity<List<Question>> getAll() {
        return  questionService.getAllQuestion();
    }

    @GetMapping("category/{cat}")
    public ResponseEntity<List<Question>> getCateQuestion(@PathVariable("cat") String category) {
        return questionService.getAllQuestionByCate(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);

    }
}
