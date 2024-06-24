package com.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.spring.model.UserVO;
import com.spring.service.OttService;
import com.spring.service.UserService;

@Controller
@RequestMapping(value="admin/user")
public class UserPageController {

	private static final Logger log = LoggerFactory.getLogger(UserPageController.class);
	
	@Autowired
	private UserService userservice;

	
	@RequestMapping(value="/UserList", method= RequestMethod.GET)
	public void userListGET(Model model) throws Exception {
		log.info("사용자 리스트 목록");
		
		List<UserVO> list = userservice.userGetList();
		model.addAttribute("list", list);
		
	}
	
	@GetMapping("/UserInfo")
	public void userInfoGET(String userEmail, Model model) throws Exception{
		log.info("사용자 상세 정보" + userEmail);
		model.addAttribute("userInfo", userservice.userGetInfo(userEmail));
		
		List<UserVO> list = userservice.userottlist(userEmail);
		model.addAttribute("userottlist", list);
		System.out.println(userservice.userottlist(userEmail));
	}
	
	
	
}
