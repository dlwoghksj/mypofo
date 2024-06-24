package com.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.UserMapper;
import com.spring.model.UserVO;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public List<UserVO> userGetList() throws Exception {
		return userMapper.userGetList();
	}


	@Override
	public UserVO userGetInfo(String userEmail) throws Exception {
		log.info("(service)userGetInfo()........" + userEmail);
		return userMapper.userGetInfo(userEmail);
	}


	@Override
	public List<UserVO> userottlist(String userEmail) throws Exception {
		log.info("userottlist" + userEmail);
		return userMapper.userottlist(userEmail);
	}


}
