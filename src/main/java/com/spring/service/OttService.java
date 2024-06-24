package com.spring.service;

import java.util.List;

import com.spring.model.OttVO;

public interface OttService {
	public List<OttVO> ottGetGroup() throws Exception;
	
	public List<OttVO> ottGetUser(String groupCode) throws Exception;
	
	public OttVO ottGetInfo(String groupCode) throws Exception;
	
	public List<OttVO> ottCodeList(String ottCode) throws Exception;
	
	public int ottUpdateInfo(OttVO ott) throws Exception;
	
	public List<OttVO> waitingList() throws Exception;
	
}
