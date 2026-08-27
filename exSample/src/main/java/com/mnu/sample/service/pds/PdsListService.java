package com.mnu.sample.service.pds;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.BoardDTO;
import com.mnu.sample.model.PdsDAO;
import com.mnu.sample.model.PdsDTO;
import com.mnu.sample.service.Action;
//자료실 목록
public class PdsListService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PdsDAO pDAO = PdsDAO.getInstance();
		String search="", key="";
		int totcount = 0;
		
		List<PdsDTO> pList = null;
		if(request.getParameter("key") != null) {
			//검색이용시
			search = request.getParameter("search");
			key = request.getParameter("key");
			totcount = pDAO.pdsCount(search, key);//총 게시글 수
			pList = pDAO.pdsList(search, key);//전체 글 목록			
		}else {
			//검색없이 모두 
			totcount = pDAO.pdsCount();//총 게시글 수
			pList = pDAO.pdsList();//전체 글 목록
		}

		request.setAttribute("totcount", totcount);
		request.setAttribute("pList", pList);
		request.setAttribute("search", search);
		request.setAttribute("key", key);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Pds/pds_list.jsp");
		rd.forward(request, response);

	}

}
