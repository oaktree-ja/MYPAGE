package com.christmas.member.controller;

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
 * Servlet implementation class MemberDelete
 */
@WebServlet("/member/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?
	HttpSession session = request.getSession();
	Member member = (Member)session.getAttribute("member");
	if(member !=null) {
		String memberId = member.getMemberId();
		
		MemberService mService = new MemberService();
		int result = mService.deleteMember(memberId);
		if(result >0) {
			//삭제 성공 후 메인페이지로 이동하는ㄴ데 세션 정보 파괴
			
			response.sendRedirect("/member/logout");
		}else {
			//탈퇴 실패 에러페이지로 이동
			request.getRequestDispatcher("/WEB-INF/views/common/error/error.jsp").forward(request, response);
		}
	}
		
	}
	
}

