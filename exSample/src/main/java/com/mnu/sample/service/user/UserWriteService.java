package com.mnu.sample.service.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.service.Action;

//회원가입 폼
public class UserWriteService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/User/user_insert.jsp");//회원가입 기본폼
		//RequestDispatcher rd = request.getRequestDispatcher("/User/user_insert_email.jsp");//회원가입(Email 보인인증)폼
		//RequestDispatcher rd = request.getRequestDispatcher("/User/user_insert_sms.jsp");//회원가입(SMS 보인인증)폼
		rd.forward(request, response);

	}

}
