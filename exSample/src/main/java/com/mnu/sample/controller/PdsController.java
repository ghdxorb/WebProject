package com.mnu.sample.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.service.Action;
import com.mnu.sample.service.pds.PdsDeleteProService;
import com.mnu.sample.service.pds.PdsDeleteService;
import com.mnu.sample.service.pds.PdsDownService;
import com.mnu.sample.service.pds.PdsListService;
import com.mnu.sample.service.pds.PdsModifyProService;
import com.mnu.sample.service.pds.PdsModifyService;
import com.mnu.sample.service.pds.PdsViewService;
import com.mnu.sample.service.pds.PdsWriteProService;
import com.mnu.sample.service.pds.PdsWriteService;

/**
 * Servlet implementation class PdsController
 */
@WebServlet("/Pds")
public class PdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PdsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cmd = request.getParameter("cmd");
		System.out.println("자료실 요청 : " + cmd);

		Action action = null;
		if(cmd.equals("pdsList")) {//목록
			action = new PdsListService();
		}else if(cmd.equals("pdsWrite")) {//등록폼
			action = new PdsWriteService();
		}else if(cmd.equals("pdsWritePro")) {//등록처리
			action = new PdsWriteProService();
		}else if(cmd.equals("pdsView")) { //상세정보(자세히보기)
			action = new PdsViewService();
		}else if(cmd.equals("pdsModify")) {//수정폼
			action = new PdsModifyService();
		}else if(cmd.equals("pdsModifyPro")) {//수정처리
			action = new PdsModifyProService();
		}else if(cmd.equals("pdsDelete")) { //삭제폼
			action = new PdsDeleteService();
		}else if(cmd.equals("pdsDeletePro")) {//삭제처리
			action = new PdsDeleteProService();
		}else if(cmd.equals("pdsDown")) {//파일다운로드처리
			action = new PdsDownService();
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
