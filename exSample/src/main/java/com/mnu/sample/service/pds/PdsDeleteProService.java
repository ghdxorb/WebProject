package com.mnu.sample.service.pds;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.PdsDAO;
import com.mnu.sample.service.Action;
//자료실 목록
public class PdsDeleteProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idx = Integer.parseInt(request.getParameter("idx"));
		String pass = request.getParameter("pass");
		
		PdsDAO pDAO = PdsDAO.getInstance();
		
		String filename = pDAO.pdsSearchFile(idx);
		
		int row = pDAO.pdsDelete(idx, pass); //
		
		if(row==1) {//성공
			// 파일 삭제
			ServletContext context = request.getServletContext();
			String path = context.getRealPath("Pds/upload/");// 파일 저장 경로
			File file = new File(path+filename);
			if(file.exists()) {
				file.delete();
			}
		}
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Pds?cmd=/pds_delete_pro.jsp");
		rd.forward(request, response);

	}

}
