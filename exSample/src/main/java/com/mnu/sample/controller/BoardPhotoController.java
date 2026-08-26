package com.mnu.sample.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.service.Action;
import com.mnu.sample.service.boardphoto.BoardPhotoDeleteProService;
import com.mnu.sample.service.boardphoto.BoardPhotoDeleteService;
import com.mnu.sample.service.boardphoto.BoardPhotoListService;
import com.mnu.sample.service.boardphoto.BoardPhotoModifyProService;
import com.mnu.sample.service.boardphoto.BoardPhotoModifyService;
import com.mnu.sample.service.boardphoto.BoardPhotoViewService;
import com.mnu.sample.service.boardphoto.BoardPhotoWriteProService;
import com.mnu.sample.service.boardphoto.BoardPhotoWriteService;

/**
 * Servlet implementation class BoardPhotoController
 */
@WebServlet("/BoardPhoto")
public class BoardPhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardPhotoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		System.out.println("포토게시판 요청 : " + cmd);

		Action action = null;
		if(cmd.equals("boardPhotoList")) {  //리스트
			action = new BoardPhotoListService();
		}else if(cmd.equals("boardPhotoWrite")) {//등록폼
			action = new BoardPhotoWriteService();
		}else if(cmd.equals("boardPhotoWritePro")) {//등록처리
			action = new BoardPhotoWriteProService();
		}else if(cmd.equals("boardPhotoView")) {// 뷰(상세보기)
			action = new BoardPhotoViewService();
		}else if(cmd.equals("boardPhotoModify")) {//수정폼
			action = new BoardPhotoModifyService();
		}else if(cmd.equals("boardPhotoModifyPro")) {//수정처리
			action = new BoardPhotoModifyProService();
		}else if(cmd.equals("boardPhotoDelete")) {//삭제폼(비번입력)
			action = new BoardPhotoDeleteService();
		}else if(cmd.equals("boardPhotoDeletePro")) {//삭제처리
			action = new BoardPhotoDeleteProService();
		}

		
		action.process(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		doGet(request, response);
	}

}
