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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// UPDATE MEMBER_TBL SET EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?;
		String memberId = request.getParameter("memberId");
		String email	= request.getParameter("email");
		String phone	= request.getParameter("phone");
		String address  = request.getParameter("address");
		String hobby	= request.getParameter("hobby");
		Member member = new Member(memberId, email, phone, address, hobby);
		System.out.println(member.toString());
		MemberService mService = new MemberService();
		int result = mService.updateMember(member);
		if(result > 0) {
			// ������Ʈ ����
			// ������������ �̵��ϰ� ������ mypage.jsp�� ��������ϰ�
			// mypage.jsp���� ��񿡼� ������ ���� �ʿ��ϹǷ� ������ ���� �ڵ带 �ۼ�
			// ������ �̹� MyPageServlet�� �ش� �ڵ尡 �ۼ��Ǿ� �����Ƿ�
			// sendRedirect() �޼ҵ带 �̿��ؼ� MyPageServlet�� ȣ���ϴ� ������ ��ü ����~
//			member = mService.selectOneById(memberId);
//			request.setAttribute("member", member);
//			request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp")
//			.forward(request, response);
			response.sendRedirect("/member/mypage");
			
			//response.sendRedirect("/");
		}else {
			// ������Ʈ ����
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp")
			.forward(request, response);
		}
	}
}