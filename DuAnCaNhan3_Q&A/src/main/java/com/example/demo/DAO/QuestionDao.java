package com.example.demo.DAO;

import com.example.demo.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question , Integer> {

    @Query("SELECT  a from Question a where a.category=?1 ")
    List<Question> findCategory(String category);

    @Query(value = "select * from question1 q where q.category=:category order by random() limit :numQ",nativeQuery = true)
    List<Question> findRanDomQuestionCategory(String category, int numQ);
}
