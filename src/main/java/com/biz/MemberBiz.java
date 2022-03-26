package com.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MemberDAO;
import com.vo.MemberVO;

@Service
public class MemberBiz {
	@Autowired
	private MemberDAO dao;

//	public int insertMember (MemberVO vo) {
//		return dao.insertMember(vo);
//	}

//	public List<MemberVO> listMember() {
//		System.out.println("biz왔다.");
//		// TODO Auto-generated method stub
//		return dao.listMember();
//	}
	
	public MemberVO checkLogin(String id, String pw) {
		System.out.println("로그인체크 biz");
		return dao.checkLogin(id, pw);
	}

	public String findPw(String id, String email) {
		// TODO Auto-generated method stub
		System.out.println("비번찾기 biz");
		return dao.findPw(id, email);
	}

	public String findId(String name, String email) {
		// TODO Auto-generated method stub
		System.out.println("아이디찾기 biz");
		return dao.findId(name, email);
	}

	public int insertMember(MemberVO vo) {
		System.out.println("회원가입 biz");
		return dao.insertMember(vo);
	}
	public int existMember(String id) {
		System.out.println("존재하는지 체크 biz");
		return dao.existMember(id);
	}

	public int modifyinfo(String email, String password, String tel, String id) {
		System.out.println("회원정보수정 biz");
		return dao.modifyinfo(email, password, tel, id);
	}
}