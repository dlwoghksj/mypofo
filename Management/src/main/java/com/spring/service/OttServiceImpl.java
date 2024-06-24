package com.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mapper.OttMapper;
import com.spring.model.OttVO;

@Service
public class OttServiceImpl implements OttService{
	private static final Logger log = LoggerFactory.getLogger(OttServiceImpl.class);
	
	@Autowired
	OttMapper ottMapper;
	
	@Override
	public List<OttVO> ottGetUser(String groupCode) throws Exception {
		return ottMapper.ottGetUser(groupCode);
	}

	@Override
	public OttVO ottGetInfo(String groupCode) throws Exception {
		return ottMapper.ottGetInfo(groupCode);
	}

	@Override
	public List<OttVO> ottCodeList(String ottCode) throws Exception {
		return ottMapper.ottCodeList(ottCode);
	}

	@Override
	public int ottUpdateInfo(OttVO ott) throws Exception {
		return ottMapper.ottUpdateInfo(ott);
	}

	@Override
	public List<OttVO> ottGetGroup() throws Exception {
		return ottMapper.ottGetGroup();
	}

	@Override
	public List<OttVO> waitingList() throws Exception {
		return ottMapper.waitingList();
	}

}
