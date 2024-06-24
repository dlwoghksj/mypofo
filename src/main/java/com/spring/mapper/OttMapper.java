package com.spring.mapper;

import java.util.List;

import com.spring.model.OttVO;

public interface OttMapper {
	public List<OttVO> ottGetUser(String groupCode);
	
	public OttVO ottGetInfo(String groupCode);
	
	public List<OttVO> ottCodeList(String ottCode);

	public int ottUpdateInfo(OttVO ott);

	public List<OttVO> ottGetGroup();
	
	public List<OttVO> waitingList();
	
}
