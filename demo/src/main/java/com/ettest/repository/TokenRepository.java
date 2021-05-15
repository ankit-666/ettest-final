package com.ettest.repository;

import com.ettest.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("com.ettest.repository.TokenRepository")
public interface TokenRepository extends JpaRepository<Token, Long> {
}
