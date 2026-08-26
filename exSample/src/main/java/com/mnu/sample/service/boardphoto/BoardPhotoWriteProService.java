package com.mnu.sample.service.boardphoto;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.BoardPhotoDAO;
import com.mnu.sample.model.BoardPhotoDTO;
import com.mnu.sample.service.Action;

public class BoardPhotoWriteProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardPhotoDAO bDAO = BoardPhotoDAO.getInstance();
		BoardPhotoDTO bDTO = new BoardPhotoDTO();
		
		bDTO.setName(request.getParameter("name"));
		bDTO.setSubject(request.getParameter("subject"));
		bDTO.setContents(request.getParameter("contents"));
		bDTO.setPass(request.getParameter("pass"));
/*		
		//테스트
		System.out.println("이름 : " + bDTO.getName());
		System.out.println("제목 : " + bDTO.getSubject());
		System.out.println("내용 : " + bDTO.getContents());
		System.out.println("비번 : " + bDTO.getPass());
*/		
		int row= bDAO.boardWrite(bDTO);
		System.out.println("결과 : " + row);
		if(row==1) {
			response.sendRedirect("/BoardPhoto?cmd=boardPhotoList");
		}else {
			response.sendRedirect("/BoardPhoto?cmd=boardPhotoWrite");
		}
		//RequestDispatcher rd = request.getRequestDispatcher("/BoardPhoto/board_list.jsp");
		//rd.forward(request, response);

	}

}
