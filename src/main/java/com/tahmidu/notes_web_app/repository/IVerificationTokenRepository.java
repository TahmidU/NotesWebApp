package com.tahmidu.notes_web_app.repository;

import com.tahmidu.notes_web_app.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query(value = "SELECT * FROM verification_token vt WHERE vt.token = (:token) AND vt.user_id = (:userId) " +
            "AND vt.expiry >= (:currentTime) AND vt.is_active = 1", nativeQuery = true)
    List<VerificationToken> getActiveTokens(int token, long userId, long currentTime);
}
