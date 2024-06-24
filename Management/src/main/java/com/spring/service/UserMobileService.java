package com.spring.service;

import java.util.List;

import com.spring.model.UserMobileVO;

public interface UserMobileService {
	public UserMobileVO userLogin(UserMobileVO user) throws Exception;
	public UserMobileVO userProfile(UserMobileVO user) throws Exception;
	public int userUpdate(UserMobileVO user) throws Exception;
	public List<UserMobileVO> groupUserList(UserMobileVO user) throws Exception;
	public UserMobileVO userWaiting(UserMobileVO user) throws Exception;
	public int userIDCK(UserMobileVO user) throws Exception;
	public void userJoin(UserMobileVO user) throws Exception;
	public int waitingCancel(UserMobileVO user) throws Exception;
	public int subscriptionCancel(UserMobileVO user) throws Exception;
	public void giriboy() throws Exception;
	public int OttCK(UserMobileVO user) throws Exception;
	public int waitingCK(UserMobileVO user) throws Exception;
	public void OttSubscription(UserMobileVO user) throws Exception;
}
