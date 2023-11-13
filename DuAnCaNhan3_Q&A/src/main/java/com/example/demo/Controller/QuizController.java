package com.example.demo.Controller;

import com.example.demo.Entities.Question;
import com.example.demo.Entities.QuestionWrapper;
import com.example.demo.Entities.Respone;
import com.example.demo.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>CreateQuiz(@RequestParam String category ,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);

    }
@GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuizQuestion(@PathVariable Integer id){
        return  quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Respone> respones){
        return quizService.Caculate(id,respones);
    }
}
