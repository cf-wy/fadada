package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import com.example.demo.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	@Autowired
	private UserRepository repository;

	//缓存的key由KeyGenerator自动生成
	@Override
	@Cacheable(value="user-key",unless="#result == null",keyGenerator="keyGenerator")
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return repository.findByUserName(name);
	}

	@Override
	@Cacheable(value="user-key",keyGenerator="keyGenerator")
	public List<User> findUsersByNickName(String nickName) {
		// TODO Auto-generated method stub
		return repository.findUsersByNickName(nickName);
	}

	@Override
	public Page<User> findUsersByPage() {
		int page=0,size=10;
		Sort sort = new Sort(Direction.DESC, "id");
	    Pageable pageable = new PageRequest(page, size,sort);
	    Page<User> pages=repository.findAll(pageable);
		return  pages;
	} 

}
