package com.spring.mapper;

import java.util.List;

import com.spring.model.ManagerVO;

public interface ManagerMapper {
	//회원가입 
	public void ManagerJoin(ManagerVO manager);

	//매니저 등록번호 확인 
	public int managerCK(String managerCode);
	//매니저 등록 중복 체크 
	public int IDCK(String managerCode);
	
	//로그인 
	public ManagerVO ManagerLogin(ManagerVO manager);

	public List<ManagerVO> ManagerList();
	
	public int managerDelete(String managerCode);
}
