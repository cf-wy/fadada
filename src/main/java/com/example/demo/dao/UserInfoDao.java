package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer>{
	UserInfo findByUsername(String userName);
}
