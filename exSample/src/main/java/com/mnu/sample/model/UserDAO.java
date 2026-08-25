package com.mnu.sample.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mnu.sample.util.DBManager;

public class UserDAO {
	private UserDAO() {}
	private static UserDAO notice = new UserDAO();
	public static UserDAO getInstance() {
		return notice;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	//아이디 중복 검사 메소드
	public int userIdCheck(String userid){
		int count = 0;
		String sql="select count(*) from tbl_user where userid=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}
	
	//회원등록 메소드
	public int userWrite(UserDTO uDTO){
		int count = 0;
		String sql="insert into tbl_user(name, userid, passwd, tel, email) "
				+ " values(?,?,?,?,?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uDTO.getName());
			pstmt.setString(2, uDTO.getUserid());
			pstmt.setString(3, uDTO.getPasswd());
			pstmt.setString(4, uDTO.getTel());
			pstmt.setString(5, uDTO.getEmail());
			
			count = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return count;
	}
	
	//로그인 메소드(로그인 성공시 1, 실패시 0 반환)
	public int userLogin(UserDTO uDTO) {
		int count = 0;
		String sql="select count(*) from tbl_user "
				+ "	where userid=? and passwd=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uDTO.getUserid());
			pstmt.setString(2, uDTO.getPasswd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return count;
	}

	//ID를 이용한 사용자 검색)
	public UserDTO userLoginDTO(String userid) {
		UserDTO user = null;
		String sql="select * from tbl_user "
				+ "	where userid=? ";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setName(rs.getString("name"));
				user.setUserid(rs.getString("userid"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setFirst_time(rs.getString("first_time"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	//로그인 메소드(로그인 성공시 DTO, 실패시 null 반환)
	public UserDTO userLoginDTO(UserDTO uDTO) {
		UserDTO user = null;
		String sql="select * from tbl_user "
				+ "	where userid=? and passwd=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uDTO.getUserid());
			pstmt.setString(2, uDTO.getPasswd());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDTO();
				user.setName(rs.getString("name"));
				user.setUserid(rs.getString("userid"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setFirst_time(rs.getString("first_time"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

}
