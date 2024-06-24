package com.spring.service;

import java.util.List;

import com.spring.model.ManagerVO;

public interface ManagerService {

	//회원가입 
	public void ManagerJoin(ManagerVO manager) throws Exception;

	//메니저 등록 확인 
	public int managerCK(String managerCode) throws Exception;
	
	//메니저 중복 체크 
	public int IDCK(String managerCode) throws Exception;
	
	//로그인 
	public ManagerVO ManagerLogin(ManagerVO manager) throws Exception;
	
	public List<ManagerVO> ManagerList() throws Exception;
	
	public int managerDelete(String managerCode) throws Exception;
}
