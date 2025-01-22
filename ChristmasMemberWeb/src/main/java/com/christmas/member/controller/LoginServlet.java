package com.christmas.member.controller;


import java.io.IOException;

import com.christmas.member.model.service.MemberService;
import com.christmas.member.model.vo.Member;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/login.jsp");
		view.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mService = new MemberService();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		Member member = new Member(memberId, memberPw);
		member = mService.selectOneByLogin(member);
		if(member != null) {
			HttpSession session = request.getSession(); // 서버측 연결(저장공간)
			session.setAttribute("member", member);
			
			
			// 로그인 성공! -> 메인페이지 이동, 게시판 페이지 이동
			// http://localhost:8888/, 			http://localhost:8888/board
			// url을 이용한 페이지 이동시 response 객체 사용
			response.sendRedirect("/"); // sendRedirect() 메소드 호출
		}else {
			// 로그인 실패.. -> error.jsp로 이동
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}
}