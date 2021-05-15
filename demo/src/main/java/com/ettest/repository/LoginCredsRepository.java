package com.ettest.repository;

import com.ettest.entity.LoginCreds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("com.ettest.repository.LoginCredsRepository")
public interface LoginCredsRepository extends JpaRepository<LoginCreds, Long> {
}
