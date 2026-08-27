package com.mnu.sample.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mnu.sample.util.DBManager;

public class BoardDAO {
	private BoardDAO() {}
	private static BoardDAO board = new BoardDAO();
	public static BoardDAO getInstance() {
		return board;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 총 게시글 수 카운트 메소드
	public int boardCount(){
		int count = 0;
		String sql="select count(*) from tbl_board";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
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
	
	// 총 게시글 수 카운트 메소드(검색기능추가)
	public int boardCount(String search, String key){
		int count = 0; 
		String sql="select count(*) from tbl_board where " + search + " like ? ";
/*		
		if(search.equals("name")) {
		     sql="select count(*) from tbl_board where name like ? ";
		}else if(search.equals("subject")) {
		     sql="select count(*) from tbl_board where subject like ? ";
		}else {
			 sql="select count(*) from tbl_board where contents like ? ";
		}
*/		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
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
	
	//전체 게시글 목록(list)-(검색, 페이지인덱스 없음) 메소드
	public List<BoardDTO> boardList(){
		List<BoardDTO> bList = new ArrayList();
		String sql="select * from tbl_board order by regdate desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO bDTO = new BoardDTO();
				bDTO.setIdx(rs.getInt("idx"));
				bDTO.setName(rs.getString("name"));				
				bDTO.setSubject(rs.getString("subject"));
				bDTO.setRegdate(rs.getString("regdate"));
				bDTO.setReadcnt(rs.getInt("readcnt"));
				
				bList.add(bDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bList;
	}

	//전체 게시글 목록(list)-(검색 조건 추가) 메소드
	public List<BoardDTO> boardList(String search, String key){
		List<BoardDTO> bList = new ArrayList();
		String sql="select * from tbl_board where " 
					+ search + " like ? order by regdate desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO bDTO = new BoardDTO();
				bDTO.setIdx(rs.getInt("idx"));
				bDTO.setName(rs.getString("name"));				
				bDTO.setSubject(rs.getString("subject"));
				bDTO.setRegdate(rs.getString("regdate"));
				bDTO.setReadcnt(rs.getInt("readcnt"));
				
				bList.add(bDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return bList;
	}
	
	//글 등록 메소드
	public int boardWrite(BoardDTO bDTO){
		int row = 0;
		String sql="insert into tbl_board(idx, name, email, subject, contents, pass) "
				+ " values(tbl_board_seq_idx.nextval, ?, ?, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getName());
			pstmt.setString(2, bDTO.getEmail());
			pstmt.setString(3, bDTO.getSubject());
			pstmt.setString(4, bDTO.getContents());
			pstmt.setString(5, bDTO.getPass());
			
			row = pstmt.executeUpdate();

		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//조회수 증가 메소드
	public void boardHits(int idx){
		String sql="update tbl_board set readcnt=readcnt+1 where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);		
			pstmt.executeUpdate();		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}	
	}
	//특정글(idx)에 해당하는 글 검색 반환
	public BoardDTO boardSearch(int idx){
		BoardDTO bDTO = new BoardDTO();
		String sql="select * from tbl_board where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);		
			rs = pstmt.executeQuery();		
			if(rs.next()) {
				bDTO.setIdx(rs.getInt("idx"));
				bDTO.setName(rs.getString("name"));				
				bDTO.setSubject(rs.getString("subject"));
				bDTO.setContents(rs.getString("contents"));
				bDTO.setRegdate(rs.getString("regdate"));
				bDTO.setReadcnt(rs.getInt("readcnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return bDTO;
	}

	//글 수정 메소드
	public int boardModify(BoardDTO bDTO){
		int row = 0;
		String sql="update tbl_board set email=?, subject=?, contents=? "
				+ "	where idx=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bDTO.getEmail());
			pstmt.setString(2, bDTO.getSubject());
			pstmt.setString(3, bDTO.getContents());
			pstmt.setInt(4, bDTO.getIdx());
			pstmt.setString(5, bDTO.getPass());
			
			row = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	//글 삭제 메소드
	public int boardDelete(int idx, String pass){
		int row = 0;
		String sql="delete from tbl_board where idx=? and pass=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.setString(2, pass);
			
			row = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
}
