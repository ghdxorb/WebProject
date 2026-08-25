package com.mnu.sample.service.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.model.UserDAO;
import com.mnu.sample.service.Action;

//RestController 형식으로 작성(JSON 타입)
public class UserIdCheckService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		//System.out.println("userid : " + userid);
		UserDAO uDAO = UserDAO.getInstance();
		int row = uDAO.userIdCheck(userid);//id 중복검사 메소드 수행
		
		PrintWriter out = response.getWriter();
		out.print(row);
		
		//System.out.println(row);
		// X forword()
		
	}

}
