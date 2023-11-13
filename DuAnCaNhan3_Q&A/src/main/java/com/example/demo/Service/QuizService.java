package com.example.demo.Service;

import com.example.demo.DAO.QuestionDao;
import com.example.demo.DAO.QuizDao;
import com.example.demo.Entities.Question;

import com.example.demo.Entities.QuestionWrapper;
import com.example.demo.Entities.Quiz;
import com.example.demo.Entities.Respone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRanDomQuestionCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionList = quiz.get().getQuestions();
        List<QuestionWrapper> questionforUser = new ArrayList<>();
        for (Question q : questionList) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getLevel(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionforUser.add(qw);
        }
        return new ResponseEntity<>(questionforUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> Caculate(Integer id, List<Respone> respones) {
        Quiz quiz=quizDao.findById(id).get();
        List<Question> questionList=quiz.getQuestions();
        int right=0;
        int i =0;
        for(Respone respone:respones){
            if(respone.getRespone().equals(questionList.get(i).getAnswer()))
                right++;
                i++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
