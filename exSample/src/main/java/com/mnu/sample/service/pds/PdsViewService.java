package com.mnu.sample.service.pds;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.PdsDAO;
import com.mnu.sample.model.PdsDTO;
import com.mnu.sample.service.Action;
//자료실 목록
public class PdsViewService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		PdsDAO pDAO = PdsDAO.getInstance();
		// 쿠키 존재 유무 검사
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();
		//쿠키 존재 유무
		for(int i=0; i<cookies.length;i++) {
			info = cookies[i];
			if(info.getName().equals("Pds"+idx)) {
				bool = true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();
		if(!bool) {//쿠키가 없으면
			//idx를 이용한 조회수 증가
			pDAO.pdsHits(idx);
			//쿠키생성
			info = new Cookie("Pds"+idx, newValue);
			//쿠키유효기간(시간)
			info.setMaxAge(60*60);//(초단위지정)1시간
			response.addCookie(info);
		}
		
		//idx에 해당하는 글 검색
		PdsDTO pDTO = pDAO.pdsSearch(idx);
		pDTO.setContents(pDTO.getContents().replace("\n", "<br>"));
		
		request.setAttribute("pDTO", pDTO);

		
		RequestDispatcher rd = request.getRequestDispatcher("/Pds/pds_view.jsp");
		rd.forward(request, response);

	}

}
