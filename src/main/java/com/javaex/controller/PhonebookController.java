package com.javaex.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;


@Controller
public class PhonebookController {
	
	@Autowired
	private PhonebookService ps;
	
	//리스트폼
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		
		List<PersonVo> person = ps.exeList();
		
		model.addAttribute("person", person);
		
		return "list";
	}
	
	//수정폼
	@RequestMapping(value = "/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@Param("personId") int no, Model model) {
		
		PersonVo person = ps.exeOne(no);
		
		model.addAttribute("person", person);
		
		return "modifyForm";
	}
	
	//수정
	@RequestMapping(value = "/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute PersonVo pv) {
		
		ps.exeModify(pv);
		
		return "redirect:/list";
	}
	
	//등록폼
	@RequestMapping(value = "/writeform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm(@ModelAttribute PersonVo pv) {
		
		return "writeForm";
	}
	
	//등록
	@RequestMapping(value = "/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo pv) {
		
		ps.exeWirte(pv);
		
		return "redirect:/list"; //리스트로 리다이렉트
	}
	
	//삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@Param("no")int no) {
		
		ps.exeDelete(no);
		
		return "redirect:/list";
	}
	
}
