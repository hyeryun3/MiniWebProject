package com.controller;

import com.biz.MemberBiz;
import com.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MyController {

	private final MemberBiz biz;

	@GetMapping("join.do")
	public String join() {
		System.out.println("회원가입화면");
		return "join";
	}

	@GetMapping("index.do")
	public String loginMain() {
		System.out.println("회원가입 및 로그인 완료 후 메인으로");
		return "index";
	}

	@GetMapping("login.do")
	public String login() {
		System.out.println("로그인화면");
		return "login";
	}

	@PostMapping(value="afterjoin.do")
	public ModelAndView afterjoin(MemberVO vo, HttpSession session) {
		System.out.println("회원가입컨트롤러");
		int e = biz.existMember(vo.getId());
		int r =0;
		if(e==0) {
			r=biz.insertMember(vo);
			session.setAttribute("userId", vo.getId());
			System.out.println(r + " 가입시켜줄게");
		}else {
			System.out.println(r + " 이미존재하는 아이디");
		}
		return new ModelAndView("result","r",r);
	}

	@PostMapping("afterlogin.do")
	public String listMember(@RequestParam("id") String id, @RequestParam("pw") String pw, HttpSession session) {
		System.out.println("로그인 체크 컨트롤러");
		String str = "";
		MemberVO vo = new MemberVO();
		vo = biz.checkLogin(id, pw);

		if (vo!=null) {
			System.out.println("로그인 성공!");
			session.setAttribute("userId", vo.getId());
			str = "index";
		} else {
			System.out.println("로그인 실패!");
			str = "alert";
		}
		return str;
	}

	@PostMapping(value = "/findpw.do")
	public ModelAndView findPw(@RequestParam("pid") String id, @RequestParam("pemail") String email) {
		System.out.println("비밀번호찾기컨트롤러");
		String pw = biz.findPw(id, email);
		// ajax로 alert 띄워보자!
		return new ModelAndView("result", "str", pw);
	}

	@PostMapping(value = "/findid.do")
	public ModelAndView findId(@RequestParam("iname") String name, @RequestParam("iemail") String email) {
		System.out.println("아이디찾기컨트롤러");
		String id = biz.findId(name, email);
		// ajax로 alert 띄워보자!
		return new ModelAndView("result", "str", id);
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}

	@GetMapping("mypage.do")
	public String mypage(Model model,HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		System.out.println(userId);
		MemberVO vo = biz.findUser(userId);
		String tel1 = vo.getTel().substring(0, 3);
		String tel2 = vo.getTel().substring(3, 7);
		String tel3 = vo.getTel().substring(7,11);
		model.addAttribute("user",vo);
		model.addAttribute("tel1",tel1);
		model.addAttribute("tel2",tel2);
		model.addAttribute("tel3",tel3);
		return "mypage";
	}

	@PostMapping(value="aftermodify.do")
	public ModelAndView afterModify(MemberVO vo) {
		System.out.println("회원정보수정 컨트롤러");

		int r = biz.modifyinfo(vo.getEmail(), vo.getPassword(), vo.getTel(), vo.getId());
		return new ModelAndView("result", "r", r);
	}
	
	@GetMapping("board.do")
	public String goBoard() {
		return "board";
	}
	@GetMapping("boardform.do")
	public String boardform() {
		return "boardform";
	}
	@GetMapping("farmer_dic.do")
	public String farmer_dic() {
		return "farmer_dic";
	}
	@GetMapping("product_dic.do")
	public String product_dic() {
		return "product_dic";
	}
	@GetMapping("board_sell.do")
	public String board_sell() {
		return "board_sell";
	}
	@GetMapping("farmer_info.do")
	public String farmer_info() {
		return "farmer_info";
	}
	@GetMapping("search.do")
	public String search() {
		return "search";
	}
	

}
