package com.christmas.member.model.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.christmas.member.model.vo.Member;

public class MemberDAO {
	public Member selectOneByLogin(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query 
			= "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		Member result = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPwd());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				// rsetToMember
				String memberId = rset.getString("MEMBER_ID");
				String memberName = rset.getString("MEMBER_NAME");
				String email	  = rset.getString("EMAIL");
				result = new Member(memberId, memberName, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Member selectOneById(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		try {
			pstmt = conn.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.setString(1,memberId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rset = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rset.next()) {
				member = rsetToMember(rset);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			rset.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}
	

	private Member rsetToMember(ResultSet rset) throws SQLException{
		Member member= null;
		
		String memberId 	=rset.getString("MEMBER_ID");
		String memberPwd 	=rset.getString("MEMBER_PWD");
		String memberName 	=rset.getString("MEMBER_NAME");
		String gender 		=rset.getString("GENDER");
		int age  		 	=rset.getInt("AGE");
		String email	 	=rset.getString("EMAIL");
		String phone	 	=rset.getString("PHONE");
		String address	 	=rset.getString("ADDRESS");
		String hobby	 	=rset.getString("HOBBY");
		Date enrollDate 	=rset.getDate("ENROLL_DATE");
		
		member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
		
		
		
		
		return member;
	}

	public int insertMember(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO MEMBER_TBL VALUES(?,?,?,?,?,?,?,?,?,DEFAULT)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPwd());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getGender());
		pstmt.setInt(5, member.getAge());		// 타입 맞추기 int면 setInt()
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getHobby());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}
	
	public int updateMember(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE MEMBER_TBL SET EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPhone());
		pstmt.setString(3, member.getAddress());
		pstmt.setString(4, member.getHobby());
		pstmt.setString(5, member.getMemberId());
		result = pstmt.executeUpdate();
		System.out.println(result);
		pstmt.close(); 
		return result;
	}

	public int deleteMember(Connection conn, String memberId) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;		
		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, memberId);
		result = pstmt.executeUpdate();
		pstmt.close(); 
		return result;
		
		
		
	}

}