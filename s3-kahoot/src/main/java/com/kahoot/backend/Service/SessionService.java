package com.kahoot.backend.Service;

import com.kahoot.backend.DTO.LeaderboardDTO;
import com.kahoot.backend.Entity.Session;
import com.kahoot.backend.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
@Service
public class SessionService
{
    @Autowired
    SessionRepository sessionRepository;

    public Integer createSession(Session s)
    {
        return sessionRepository.save(s).getSessionId();
    }
    public Boolean checkForSession(Integer id)
    {
        if (sessionRepository.checkForSession(id)==0){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Page<LeaderboardDTO> getAllSessions(Integer page, Integer size)
    {
        Pageable pageable = PageRequest.of(page,size);
        return sessionRepository.getLeaderBoard(pageable);
    }
}