package com.kahoot.backend.Repository;
import com.kahoot.backend.DTO.ResultStatsDTO;
import com.kahoot.backend.Entity.Responses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsesRepository extends JpaRepository<Responses,Integer>
{
    @Query(nativeQuery = true)
    public List<ResultStatsDTO> questionResults(Integer qId);
}
