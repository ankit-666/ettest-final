package com.ettest.repository;

import com.ettest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("com.ettest.repository.UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

}
