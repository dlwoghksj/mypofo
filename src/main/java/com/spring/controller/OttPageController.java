package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.OttVO;
import com.spring.service.OttService;

@Controller
@RequestMapping(value="admin/ott")
public class OttPageController {
	
	private static final Logger log = LoggerFactory.getLogger(UserPageController.class);
	
	@Autowired
	private OttService ottservice;
	
	@GetMapping("/OttList")
	public void ottGroupGet(Model model) throws Exception{
		log.info("그룹 리스트");
		List<OttVO> ottgroup = ottservice.ottGetGroup();
		model.addAttribute("ottgroup", ottgroup);
	}
	
	@GetMapping({"/OttInfo", "/OttUpdateInfo"})
	public void ottUserGET(String groupCode, Model model) throws Exception{
		log.info("그룹 상세 정보" + groupCode);
		List<OttVO> ottuser = ottservice.ottGetUser(groupCode);
		model.addAttribute("ottuser", ottuser);
		
		OttVO ottInfo = ottservice.ottGetInfo(groupCode);
		model.addAttribute("ottInfo", ottInfo);
		
	}
	
	@GetMapping("/WaitingList")
	public void waitingListGet(Model model) throws Exception{
		log.info("대기자 목록 ");
		List<OttVO> waitingList = ottservice.waitingList();
		model.addAttribute("waitingList", waitingList);
	}

	@RequestMapping(value = "/Netflix", method = RequestMethod.GET)
	public void NetflixListGET(Model model) throws Exception {
		
		log.info("Netflix");
		String ottCode = "1";
		List<OttVO> list = ottservice.ottCodeList(ottCode);
		model.addAttribute("Netflix", list);
	}
	
	@RequestMapping(value = "/Wavve", method = RequestMethod.GET)
	public void WavveListGET(Model model) throws Exception {
		
		log.info("Wavve");
		String ottCode = "2";
		List<OttVO> list = ottservice.ottCodeList(ottCode);
		model.addAttribute("Wavve", list);
	}
	
	@RequestMapping(value = "/Disney", method = RequestMethod.GET)
	public void DisneyListGET(Model model) throws Exception {
		
		log.info("Disney");
		String ottCode = "3";
		List<OttVO> list = ottservice.ottCodeList(ottCode);
		model.addAttribute("Disney", list);
	}
	
	@RequestMapping(value = "/Watcha", method = RequestMethod.GET)
	public void WatchaListGET(Model model) throws Exception {
		
		log.info("Watcha");
		String ottCode = "4";
		List<OttVO> list = ottservice.ottCodeList(ottCode);
		model.addAttribute("Watcha", list);
	}
	
	@PostMapping(value="/OttUpdateInfo")
	public String OttUpdateInfoPost(OttVO ott, HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		int result = ottservice.ottUpdateInfo(ott);
		
		if(result == 1) {
			rttr.addFlashAttribute("msg", "정보 수정이 완료되었습니다. ");
			rttr.addFlashAttribute("url", "/admin/ott/OttList");
			request.setAttribute("url", "/admin/ott/OttList");
			return "redirect:/Message";
		}
		return null;
		
	}
	
}
