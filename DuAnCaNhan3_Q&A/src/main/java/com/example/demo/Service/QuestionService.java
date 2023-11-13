package com.example.demo.Service;

import com.example.demo.DAO.QuestionDao;
import com.example.demo.Entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao dao;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>( dao.findAll(), HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getAllQuestionByCate(String category) {
        return  new ResponseEntity<> (dao.findCategory(category),HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        dao.save(question);
        return  new ResponseEntity<> ("success",HttpStatus.CREATED);
    }
}
