package com.christmas.member.controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.christmas.member.model.service.MemberService;
import com.christmas.member.model.vo.Member;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/member/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/signup.jsp")
		.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId	  	= request.getParameter("memberId");
		String memberPwd	= request.getParameter("memberPwd");
		String memberName 	= request.getParameter("memberName");
		String gender		= request.getParameter("gender");
		int    age			= Integer.parseInt(request.getParameter("age"));
		String email		= request.getParameter("email");
		String phone		= request.getParameter("phone");
		String address		= request.getParameter("address");
		String hobby		= request.getParameter("hobby");
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby);
		MemberService mService = new MemberService();
		int result = mService.insertMember(member);
		if(result > 0) {
			// 회원가입 성공 -> 로그인 페이지로 이동 (/WEB-INF/views/member/login.jsp) -> /member/login
//			request.getRequestDispatcher("/WEB-INF/views/member/login.jsp")
//			.forward(request, response);
			response.sendRedirect("/member/login"); // get요청 -> doGet이 동작할거임.
		}else {
			// 회원실패 -> 에러페이지로 이동(/WEB-INF/views/common/error/error.jsp)
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}
}