package com.kahoot.backend.Repository;

import com.kahoot.backend.DTO.LeaderboardDTO;
import com.kahoot.backend.Entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;


@Repository
public interface SessionRepository extends JpaRepository<Session,Integer>
{
    @Query(value = "SELECT COUNT(*) FROM session WHERE session_id = :session_id ;",nativeQuery = true)
    public Integer checkForSession(@Param("session_id") Integer sessionId);

    @Query("SELECT new com.kahoot.backend.DTO.LeaderboardDTO(s.sessionId, s.hostName, s.topic) FROM Session s")
    Page<LeaderboardDTO> getLeaderBoard(Pageable pageable);
}
