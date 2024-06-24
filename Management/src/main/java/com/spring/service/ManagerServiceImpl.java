package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.ManagerMapper;
import com.spring.model.ManagerVO;

@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	ManagerMapper managermapper;
	
	@Override
	public void ManagerJoin(ManagerVO manager) throws Exception{
		managermapper.ManagerJoin(manager);
	}

	@Override
	public ManagerVO ManagerLogin(ManagerVO manager) throws Exception{
		return managermapper.ManagerLogin(manager);
	}

	@Override
	public int IDCK(String managerCode) throws Exception {
		return managermapper.IDCK(managerCode);
	}

	@Override
	public int managerCK(String managerCode) throws Exception {
		return managermapper.managerCK(managerCode);
	}

	@Override
	public List<ManagerVO> ManagerList() throws Exception {
		return managermapper.ManagerList();
	}

	@Override
	public int managerDelete(String managerCode) throws Exception{
		return managermapper.managerDelete(managerCode);
	}

	

}
