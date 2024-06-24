package com.spring.mapper;

import java.util.List;

import com.spring.model.UserVO;

public interface UserMapper {
	public List<UserVO> userGetList();
	
	public UserVO userGetInfo(String userEmail);
	
	public List<UserVO> userottlist(String userEmail);

}
