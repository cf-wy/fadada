package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.domain.UserInfo;
import com.example.demo.service.UserInfoService;
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoDao userInfoDao;
	@Override
	public UserInfo findByUsername(String userName) {
		// TODO Auto-generated method stub
		return userInfoDao.findByUsername(userName);
	}

}
