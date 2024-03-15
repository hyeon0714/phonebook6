package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.PhonebookService;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("api")
public class AjaxController {
	
	@Autowired
	private PhonebookService ps;
	
	@RequestMapping("/phonebook")
	public String list2() {
		
		return "list2";
	}
	
	@ResponseBody
	@RequestMapping(value = "/phonebooks", method = RequestMethod.GET)
	public List<PersonVo> getlist() {
		
		System.out.println("dddd");
		List<PersonVo> personVo =  ps.exeList();
		
		System.out.println(personVo);
		
		return personVo;
	}
	
	@ResponseBody
	@RequestMapping(value = "/phonebooks", method = RequestMethod.DELETE)
	public PersonVo getdelete(@RequestBody PersonVo pv) {
		
		System.out.println(pv);
		//ps.exeDelete(pv.getPersonId());
		
		return pv;
	}
}
