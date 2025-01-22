package com.christmas.member.model.service;
import java.sql.Connection;
import java.sql.SQLException;

import com.christmas.member.common.JDBCTemplate;
import com.christmas.member.model.dao.MemberDAO;
import com.christmas.member.model.vo.Member;


public class MemberService {

	private JDBCTemplate jdbcTemplate;
	private MemberDAO mDao;

	public MemberService() {
		jdbcTemplate = JDBCTemplate.getInstance();
		mDao = new MemberDAO();
	}

	public Member selectOneByLogin(Member member) {
		Connection conn = null;
		Member result = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.selectOneByLogin(conn, member);
		} catch (SQLException e) {                                                                                                                                                                                                               
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member selectOneById(String memberId) {
		Member member = null;
		Connection conn = null;
		
		try {
			conn = jdbcTemplate.getConnection();
			member = mDao.selectOneById(conn, memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return member;
	}
	public int insertMember(Member member) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.insertMember(conn, member);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateMember(Member member) {
		int result = 0;
		Connection conn = null;
		try {
			conn = jdbcTemplate.getConnection();
			result = mDao.updateMember(conn, member);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = null;
		try {
			conn= jdbcTemplate.getConnection();
			result = mDao.deleteMember(conn,memberId);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


}