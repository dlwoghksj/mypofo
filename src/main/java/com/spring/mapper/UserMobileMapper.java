package com.spring.mapper;

import java.util.List;

import com.spring.model.UserMobileVO;

public interface UserMobileMapper {
	public UserMobileVO userLogin(UserMobileVO user);
	
	public int userUpdate(UserMobileVO user);
	
	public List<UserMobileVO> groupUserList(UserMobileVO user);
	
	public UserMobileVO userProfile(UserMobileVO user);
	
	public UserMobileVO userWaiting(UserMobileVO user);
	
	public int userIDCK(UserMobileVO user);
	
	public void userJoin(UserMobileVO user);
	
	public int waitingCancel(UserMobileVO user);
	
	public int subscriptionCancel(UserMobileVO user);
	
	public void giriboy();
	
	public int OttCK(UserMobileVO user);
	public int waitingCK(UserMobileVO user);
	
	public void OttSubscription(UserMobileVO user);
	
}
