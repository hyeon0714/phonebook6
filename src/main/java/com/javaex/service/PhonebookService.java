package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;


@Service
public class PhonebookService {
	
	@Autowired
	private PhonebookDao pd;

	//등록
	public int exeWirte(PersonVo pv) {
		
		return pd.write(pv);
	}
	
	//리스트
	public List<PersonVo> exeList() {

		return pd.list();
	}
	
	//수정폼 한명만
	public PersonVo exeOne(int no) {
		
		return pd.one(no);
	}
	
	//수정
	public void exeModify(PersonVo pv) {
		
		pd.modify(pv);
	}
	
	//삭제
	public void exeDelete(int no) {
		
		pd.delete(no);
	}
}
