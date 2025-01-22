package com.christmas.member.common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JDBCTemplate {
	
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "student";
	private static final String PASSWORD = "student";
	private static JDBCTemplate instance;
	
	private JDBCTemplate() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection conn 
			= DriverManager
				.getConnection(URL, USERNAME, PASSWORD);
		return conn;
	}
}
