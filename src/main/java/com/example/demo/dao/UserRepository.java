package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);

	User findByUserNameOrEmail(String username, String email);
	
	List<User> findUsersByNickName(String nickName);
}
