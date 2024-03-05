package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PersonVo;

@Repository
public class PhonebookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//등록
	public int write(PersonVo pv) {
		
		int count = sqlSession.insert("phonebook.insert", pv);
		
		return count;
	}
	
	//리스트
	public List<PersonVo> list() {
		
		return sqlSession.selectList("phonebook.list");
	}
	
	//수정폼 한명만
	public PersonVo one(int no) {
		
		return sqlSession.selectOne("phonebook.one", no);
	}
	
	//수정
	public void modify(PersonVo pv) {
		
		sqlSession.update("phonebook.modify", pv);
	}
	
	//삭제
	public void delete(int no) {
		
		sqlSession.delete("phonebook.delete", no);
	}
}
