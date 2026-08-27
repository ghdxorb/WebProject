package com.mnu.sample.service.pds;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mnu.sample.service.Action;
//자료실 목록
public class PdsDownService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String filename = URLDecoder.decode(request.getParameter("filename")); //파일 이름을 받는다.
		String filename = request.getParameter("filename"); //파일 이름을 받는다.
		//서버상의 실제 경로 찾기
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("Pds/upload/");
		//String path = request.getRealPath("/") + "Pds/upload/";
		
		File file = new File(path+"/"+filename); // 절대경로입니다.

		response.setContentType("application/unknown");  //화일형태
		//response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename));
		response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, StandardCharsets.UTF_8.toString()));
	
		try{
			byte b[] = new byte[5 * 1024 * 1024];  //5M byte까지 업로드가 가능하므로 크기를 이렇게 잡아주었음.
			if (file.isFile()){
				BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));  
				BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());  
				try { 
					int read = 0;  
					while ((read = fin.read(b)) != -1){
						outs.write(b,0,read);
					}
					outs.flush();
					outs.close();  
					fin.close();         
				}catch(Exception e){}       
			}
		}catch(IllegalStateException se){}

	}

}
