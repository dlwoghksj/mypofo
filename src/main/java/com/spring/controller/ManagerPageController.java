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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.model.ManagerVO;
import com.spring.service.ManagerService;

@Controller
@RequestMapping(value="admin/manager")
public class ManagerPageController {
private static final Logger log = LoggerFactory.getLogger(UserPageController.class);
	
	@Autowired
	private ManagerService managerservice;
	
	@GetMapping("/ManagerList")
	public void managerListGet(Model model) throws Exception{
		log.info("매니저 리스트");
		List<ManagerVO> managerList = managerservice.ManagerList();
		model.addAttribute("managerList", managerList);
	}
	
	@PostMapping(value="/ManagerDelete")
	public String managerDeletePOST(String managerCode, HttpServletRequest request, RedirectAttributes rttr) throws Exception{
		int result = managerservice.managerDelete(managerCode);
		System.out.println(result + "//" + managerCode);
		System.out.println(managerservice.managerDelete(managerCode));
		rttr.addFlashAttribute("result", result);
		return "redirect:/admin/manager/ManagerList";
	}
}
