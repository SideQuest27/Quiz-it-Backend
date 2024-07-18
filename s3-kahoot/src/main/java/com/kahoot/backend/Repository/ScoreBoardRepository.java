package com.kahoot.backend.Repository;

import com.kahoot.backend.Entity.ScoreBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScoreBoardRepository extends JpaRepository<ScoreBoard, Integer>
{
    @Query(value = "SELECT COUNT(*) FROM score_board WHERE user_name = :user_name AND session_id = :session_id ;", nativeQuery = true)
    public Integer checkForSameUserName(@Param("user_name") String userName, @Param("session_id") Integer sessionId);

    @Modifying
    @Query(value = "UPDATE score_board SET score = score + :score WHERE user_id = :user_id ;", nativeQuery = true)
    public Integer updateScore(@Param("user_id") Integer userId, @Param("score") Integer score);

    @Query(value = "SELECT * FROM score_board WHERE session_id = ?1 ORDER BY score DESC ;",nativeQuery = true)
    List<ScoreBoard> scoreBoardRanking(Integer sessionId);

    @Query(value = "SELECT score FROM score_board WHERE user_id = :user_id ;",nativeQuery = true)
    Integer getTotalScore(@Param("user_id") Integer userId);
}