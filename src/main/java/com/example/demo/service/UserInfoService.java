package com.example.demo.service;

import com.example.demo.domain.UserInfo;

public interface UserInfoService {

	UserInfo findByUsername(String userName);
}
