package com.christmas.member.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.christmas.member.model.service.MemberService;
import com.christmas.member.model.vo.Member;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/member/mypage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService mService = new MemberService();
		HttpSession session = request.getSession();
		Member sMember = (Member)session.getAttribute("member");
		String memberId = sMember.getMemberId();
		Member member = mService.selectOneById(memberId);
		if(member !=null) {
			request.setAttribute("member", member);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp");
			view.forward(request,response);

		}else {
			request.getRequestDispatcher("/WEB-INF/views/error/error.jsp")
			.forward(request,response);

		}
		
		
			}

}
