package com.spring.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.ManagerVO;
import com.spring.model.OttVO;
import com.spring.service.ManagerService;
import com.spring.service.OttService;

@Controller
@RequestMapping(value="/")
@SessionAttributes("manager")
public class HomeController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ManagerService managerservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String MainGET() {
		log.info("시작 페이지");
		return "Main";
	}
	
	@RequestMapping(value = "Login", method = RequestMethod.GET)
	public String LoginGET() {
		log.info("로그인 페이지");
		return "Login";
	}
	
	
	//로그인 
	@RequestMapping(value="Login.do", method=RequestMethod.POST)
	public String LoginPost(HttpServletRequest request, ManagerVO manager, Model model, RedirectAttributes rttr) throws Exception{
		HttpSession session = request.getSession();
		ManagerVO vo = managerservice.ManagerLogin(manager);
			
		if(vo == null) {
			log.info("로그인 실패");
			rttr.addFlashAttribute("msg", "아이디와 비밀번호를 확인해주세요. ");
			rttr.addFlashAttribute("url", "/Login");
			request.setAttribute("url", "/Login");
			return "redirect:/Message";
		}else {
			log.info("로그인 성공");
			session.setAttribute("managersession", vo);
			model.addAttribute("managerInfo", vo);
			System.out.println(vo);
			return "redirect:/admin/includes/Index";
			
		}
	}
	
	//접근권한 없음 메세지
	@RequestMapping(value="error", method = RequestMethod.GET)
	public String MainGET(HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		rttr.addFlashAttribute("msg", "No access to the page");
		rttr.addFlashAttribute("url", "/");
		request.setAttribute("url", "/");
		return "redirect:/Message";
	}
	
	@RequestMapping(value="admin/includes/Index", method = RequestMethod.GET)
	public void IndexGET() throws Exception {
		log.info("index창");
	}
	
	@RequestMapping(value="Message", method = RequestMethod.GET)
	public String Message() {
		log.info("알림창");
		return "Message";
	}
	
	//메니저 회원가입 페이지 
	@RequestMapping(value="Join", method=RequestMethod.GET)
	public String JoinGET() {
		log.info("회원가입 페이지");
		return "Join";
	}
	
	@RequestMapping(value="/Join", method=RequestMethod.POST)
	public String JoinPost(HttpServletRequest request, ManagerVO manager, RedirectAttributes rttr) throws Exception{
		managerservice.ManagerJoin(manager);
		log.info("회원가입 성공");
		rttr.addFlashAttribute("msg", "매니저 등록이 완료되었습니다. ");
		rttr.addFlashAttribute("url", "/Login");
		request.setAttribute("url", "/Login");
		return "redirect:/Message";
	}
	
	@RequestMapping(value="/check/managerCK", method=RequestMethod.POST)
	@ResponseBody
	public String managerCKPost(String managerCode) throws Exception{
		log.info("매니저 등록번호 확인");
		int result1 = managerservice.managerCK(managerCode);
		if(result1 !=0) {
			return "true";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping(value="/check/IDCK", method=RequestMethod.POST)
	@ResponseBody
	public String IDCKPost(String managerCode) throws Exception{
		log.info("매니저 등록번호 중복확인");
		int result2 = managerservice.IDCK(managerCode);
		if(result2 != 0) {
			return "fail";
		}else {
			return "true";
		}
	}
	
	@RequestMapping(value="Logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		log.info("로그아웃");
		HttpSession session = request.getSession();
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃이 완료되었습니다. 로그인 페이지로 이동합니다.");
		rttr.addFlashAttribute("url", "/Login");
		request.setAttribute("url", "/Login");
		return "redirect:/Message";
	}
	
	
	
}
