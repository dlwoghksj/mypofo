package com.spring.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.spring.model.OttVO;
import com.spring.model.UserMobileVO;
import com.spring.model.UserVO;
import com.spring.service.UserMobileService;

@Controller
public class UserMobileController {
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserMobileService service;
	
	@RequestMapping(value="UserLogin.do")
	@ResponseBody
	public JSONObject UserLoginPost(UserMobileVO user) throws Exception{
		
		log.info("UserLogin");
		UserMobileVO vo = service.userLogin(user);
		JSONObject obj = new JSONObject();
		System.out.println(user);
		System.out.println(vo);
		if(vo == null) {
			log.info("로그인 실패");
			obj.put("result", false);
			return obj;
		}else {
			log.info("로그인 성공");
			obj.put("result", true);
			obj.put("user", vo);
			return obj;
			
		}
	}
	
	@RequestMapping(value="UserProfile.do")
	@ResponseBody
	public JSONObject UserGetProfile(UserMobileVO user) throws Exception {
		service.giriboy();
		log.info("UserGetProfile");
		UserMobileVO vo = service.userProfile(user);
		JSONObject obj = new JSONObject();
		obj.put("user", vo);
		UserMobileVO vo2 = service.userWaiting(user);
		if(vo2 == null) {
			obj.put("waiting", "{}");
		}else {
			obj.put("waiting", vo2);
		}
		return obj;

	}
	
	@RequestMapping(value="UserJoin.do")
	@ResponseBody
	public JSONObject UserJoin(UserMobileVO user) throws Exception {
		log.info("User Join");
		JSONObject obj = new JSONObject();
		int result = service.userIDCK(user);
		if(result == 0) {
			service.userJoin(user);
			obj.put("result", true);
			return obj;
		}
		else {
			obj.put("result", false);
			return obj;
		}
		
	}
	
	@RequestMapping(value="UserUpdate.do")
	@ResponseBody
	public JSONObject UserUpdate(UserMobileVO user) throws Exception{
		log.info("User Update");
		JSONObject obj = new JSONObject();
		int result = service.userUpdate(user);
		System.out.println(result);
		UserMobileVO vo = service.userProfile(user);
		obj.put("user", vo);
		UserMobileVO vo2 = service.userWaiting(user);
		if(vo2 == null) {
			obj.put("waiting", "{}");
		}else {
			obj.put("waiting", vo2);
		}
		return obj;
	}
	
	@RequestMapping(value="OttCancel.do")
	@ResponseBody
	public JSONObject OttCancel(UserMobileVO user) throws Exception{
		log.info("구독 취소");
		JSONObject obj = new JSONObject();
		
		int delete = service.waitingCancel(user);
		int update = service.subscriptionCancel(user);
		System.out.println("delete : " + delete + " // update : " + update);
		service.giriboy();
		if(delete == 1 || update == 1) {
			obj.put("result", true);
			UserMobileVO vo = service.userProfile(user);
			obj.put("user", vo);
			UserMobileVO vo2 = service.userWaiting(user);
			if(vo2 == null) {
				obj.put("waiting", "{}");
			}else {
				obj.put("waiting", vo2);
			}
		}else {
			obj.put("result", false);
		}
		
	
		return obj;
		
	}
	@RequestMapping(value="OttSubscription.do")
	@ResponseBody
	public JSONObject OttSubscription(UserMobileVO user) throws Exception{
		log.info("구독하기");
		JSONObject obj = new JSONObject();
		int vo = service.OttCK(user);
		System.out.println(vo);
		int vo2 = service.waitingCK(user);
		try {
			if(vo == 0 && vo2 == 0) {
				service.OttSubscription(user);
				service.giriboy();
				obj.put("result", "1");
				UserMobileVO vo3 = service.userProfile(user);
				obj.put("user", vo3);
				UserMobileVO vo4 = service.userWaiting(user);
				if(vo4 == null) {
					obj.put("waiting", "{}");
				}else {
					obj.put("waiting", vo4);
				}
			JSONArray array = new JSONArray();
			List<UserMobileVO> groupUserList = service.groupUserList(vo3);
			System.out.println(groupUserList);
			if(groupUserList == null) {
				obj.put("group", "{}");
			}else {
				array.put(groupUserList);
				obj.put("group", array);
			}
			
				
			}else if(vo !=0 && vo2 == 0) {
				obj.put("result", "0");
			}else if(vo == 0 && vo2 != 0) {
				obj.put("result", "2");
			}
			return obj;
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result", "error");
			return obj;
		}
		
	}
	
	@RequestMapping(value="bootpay")
	public String bootpayGET() throws Exception{
		
		return "bootpay";
	}
	
}
