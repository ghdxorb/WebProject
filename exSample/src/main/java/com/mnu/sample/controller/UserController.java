package com.mnu.sample.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.service.Action;
import com.mnu.sample.service.user.UserIdCheckService;
import com.mnu.sample.service.user.UserLoginProService;
import com.mnu.sample.service.user.UserLoginService;
import com.mnu.sample.service.user.UserLogoutService;
import com.mnu.sample.service.user.UserModifyProService;
import com.mnu.sample.service.user.UserModifyService;
import com.mnu.sample.service.user.UserWriteProService;
import com.mnu.sample.service.user.UserWriteSMSService;
import com.mnu.sample.service.user.UserWriteService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/User")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		System.out.println("회원관리 요청 : " + cmd);
		// 요청 분석
		Action action = null;
		
		if(cmd.equals("userLogin")) {//로그인 폼
			action = new UserLoginService();
		}else if(cmd.equals("userLoginPro")) {//로그인처리
			action = new UserLoginProService(); 
		}else if(cmd.equals("userLogout")) {//로그아웃
			action = new UserLogoutService();
		}else if(cmd.equals("userWrite")) {//회원가입
			action = new UserWriteService();
		}else if(cmd.equals("userIdCheck")) {//id중복검사
			action = new UserIdCheckService();	
		}else if(cmd.equals("userSMSCheck")) {//핸드폰 인증
			action = new UserWriteSMSService();
		}else if(cmd.equals("userWritePro")) {//회원가입처리
			action = new UserWriteProService();
		}else if(cmd.equals("userModify")) {//회원정보수점폼
			action = new UserModifyService();
		}else if(cmd.equals("userModifyPro")) {//회원정보수정처리
			action = new UserModifyProService();
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
