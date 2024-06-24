package com.spring.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.UserMobileMapper;
import com.spring.model.UserMobileVO;

@Service
public class UserMobileServiceImpl implements UserMobileService{

	@Autowired
	UserMobileMapper mapper;
	
	@Override
	public UserMobileVO userLogin(UserMobileVO user) throws Exception {
		return mapper.userLogin(user);
	}
	@Override
	public UserMobileVO userProfile(UserMobileVO user) throws Exception {
		return mapper.userProfile(user);
	}

	@Override
	public int userIDCK(UserMobileVO user) throws Exception {
		return mapper.userIDCK(user);
	}

	@Override
	public void userJoin(UserMobileVO user) throws Exception {
		mapper.userJoin(user);
	}

	@Override
	public UserMobileVO userWaiting(UserMobileVO user) throws Exception {
		return mapper.userWaiting(user);
	}

	@Override
	public int waitingCancel(UserMobileVO user) throws Exception {
		return mapper.waitingCancel(user);
	}

	@Override
	public int subscriptionCancel(UserMobileVO user) throws Exception {
		return mapper.subscriptionCancel(user);
	}

	@Override
	public void giriboy() throws Exception {
		mapper.giriboy();
		
	}

	@Override
	public int OttCK(UserMobileVO user) throws Exception {
		return mapper.OttCK(user);
	}
	
	@Override
	public void OttSubscription(UserMobileVO user) throws Exception {
		mapper.OttSubscription(user);
	}
	@Override
	public int waitingCK(UserMobileVO user) throws Exception {
		return mapper.waitingCK(user);
	}
	@Override
	public int userUpdate(UserMobileVO user) throws Exception {
		return mapper.userUpdate(user);
	}
	@Override
	public List<UserMobileVO> groupUserList(UserMobileVO user) throws Exception {
		return mapper.groupUserList(user);
	}



}
