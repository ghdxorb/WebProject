package com.mnu.sample.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mnu.sample.model.UserDAO;
import com.mnu.sample.model.UserDTO;
import com.mnu.sample.service.Action;
import com.mnu.sample.util.UserSHA256;

public class UserLoginProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO uDAO = UserDAO.getInstance();
		UserDTO uDTO = new UserDTO();
		
		uDTO.setUserid(request.getParameter("userid"));
		uDTO.setPasswd(UserSHA256.getSHA256(request.getParameter("passwd")));
		
/*		1. 기본 로그인 방식
		int row = uDAO.userLogin(uDTO);
		UserDTO user = null;
		if(row==1){
			//id를 이용 사용자 정보 검색후 반환된는 사용자 정보를
			// user = uDAO.userLoginDTO(userid);
			//session 객체에 담어서 forword
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(600);//10분
		}
		request.setAttribute("row", row);
		RequestDispatcher rd = request.getRequestDispatcher("/User/userlogin_ok.jsp");
		rd.forward(request, response);

*/		
		//2.로그인 성공시 사용자 정보 반환 후 세션객체에 담어서 forword()
		UserDTO user = uDAO.userLoginDTO(uDTO);
		if(user != null) {
			//로그인 성공시
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(600);//10분
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/User/userlogin_pro.jsp");
		rd.forward(request, response);
	}

}
