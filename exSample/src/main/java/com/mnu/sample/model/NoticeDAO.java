package com.mnu.sample.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mnu.sample.util.DBManager;

public class NoticeDAO {
	private NoticeDAO() {}
	private static NoticeDAO notice = new NoticeDAO();
	public static NoticeDAO getInstance() {
		return notice;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 총 게시글 수 카운트 메소드
	public int noticeCount(){
		int count = 0;
		String sql="select count(*) from tbl_notice";
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
	
	// 총 게시글 수 카운트 메소드(검색 추가)
	public int noticeCount(String search, String key){
		int count = 0;
		String sql="select count(*) from tbl_notice where " + search + 
				" like ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			
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
	
	//전체 공지사항 목록(list)-(검색, 페이지인덱스 없음) 메소드
	public List<NoticeDTO> noticeList(){
		List<NoticeDTO> nList = new ArrayList();
		String sql="select * from tbl_notice order by regdate desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDTO nDTO = new NoticeDTO();
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setAdid(rs.getString("adid"));				
				nDTO.setSubject(rs.getString("subject"));
				nDTO.setRegdate(rs.getString("regdate"));
				nDTO.setReadcnt(rs.getInt("readcnt"));
				
				nList.add(nDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nList;
	}

	//전체 공지사항 목록(list)-(검색 추가) 메소드
	public List<NoticeDTO> noticeList(String search, String key){
		List<NoticeDTO> nList = new ArrayList();
		String sql="select * from tbl_notice where " + search + 
				" like ? order by regdate desc";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + key + "%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDTO nDTO = new NoticeDTO();
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setAdid(rs.getString("adid"));				
				nDTO.setSubject(rs.getString("subject"));
				nDTO.setRegdate(rs.getString("regdate"));
				nDTO.setReadcnt(rs.getInt("readcnt"));
				
				nList.add(nDTO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nList;
	}

	//공지사항 글 등록 메소드
	public int noticeWrite(NoticeDTO nDTO){
		int row = 0;
		String sql="insert into tbl_notice(idx, adid, subject, contents) "
				+ " values(tbl_notice_seq_idx.nextval, ?, ?, ?)";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nDTO.getAdid());
			pstmt.setString(2, nDTO.getSubject());
			pstmt.setString(3, nDTO.getContents());
			
			row = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
	//조회수 증가 메소드
	public void noticeHits(int idx){
		String sql="update tbl_notice set readcnt=readcnt+1 where idx=?";
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
	//공지사항 특정글(idx)에 해당하는 글 검색 반환
	public NoticeDTO noticeSearch(int idx){
		NoticeDTO nDTO = new NoticeDTO();
		String sql="select * from tbl_notice where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);		
			rs = pstmt.executeQuery();		
			if(rs.next()) {
				nDTO.setIdx(rs.getInt("idx"));
				nDTO.setAdid(rs.getString("adid"));				
				nDTO.setSubject(rs.getString("subject"));
				nDTO.setContents(rs.getString("contents"));
				nDTO.setRegdate(rs.getString("regdate"));
				nDTO.setReadcnt(rs.getInt("readcnt"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nDTO;
	}

	//공지사항 글 수정 메소드
	public int noticeModify(NoticeDTO nDTO){
		int row = 0;
		String sql="update tbl_notice set subject=?, contents=? "
				+ "	where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nDTO.getSubject());
			pstmt.setString(2, nDTO.getContents());
			pstmt.setInt(3, nDTO.getIdx());
			
			row = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}

	//공지사항 글 삭제 메소드
	public int noticeDelete(int idx){
		int row = 0;
		String sql="delete from tbl_notice where idx=?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			
			row = pstmt.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return row;
	}
	
}
