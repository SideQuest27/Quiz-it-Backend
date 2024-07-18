package com.kahoot.backend.Repository;

import com.kahoot.backend.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer>
{
    @Query(value = "SELECT q_id FROM question WHERE session_id = ?1", nativeQuery = true)
    public List<Long> getQuestionIds(Integer sessionId);
}
