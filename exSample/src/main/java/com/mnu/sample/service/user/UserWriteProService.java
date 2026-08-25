package com.mnu.sample.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.UserDAO;
import com.mnu.sample.model.UserDTO;
import com.mnu.sample.service.Action;
import com.mnu.sample.util.UserSHA256;

//회원가입 처리
public class UserWriteProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO uDAO = UserDAO.getInstance();
		UserDTO uDTO = new UserDTO();
		
		uDTO.setName(request.getParameter("name"));
		uDTO.setUserid(request.getParameter("userid"));
		//uDTO.setPasswd(request.getParameter("passwd"));
		uDTO.setPasswd(UserSHA256.getSHA256(request.getParameter("passwd")));
		uDTO.setTel(request.getParameter("tel"));
		
		int row = uDAO.userWrite(uDTO);
		
		response.sendRedirect("/User?cmd=userLogin");//로그인 페이지로 이동
		
	/*  //회원가입 성공유무 판단
		request.setAttribute("row", row);
		RequestDispatcher rd = request.getRequestDispatcher("/User/userWritePro.jsp");
		rd.forward(request, response);
	*/
	}

}
