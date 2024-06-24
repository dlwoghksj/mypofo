package com.spring.service;

import java.util.List;

import com.spring.model.UserVO;

public interface UserService {
	public List<UserVO> userGetList() throws Exception;
	public UserVO userGetInfo(String userEmail) throws Exception;
	public List<UserVO> userottlist(String userEmail) throws Exception;


}
