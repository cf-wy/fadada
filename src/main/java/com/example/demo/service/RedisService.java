package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.domain.User;

public interface RedisService {

	User getUserByName(String name); 
	List<User> findUsersByNickName(String nickName);
	Page<User> findUsersByPage();
}
