package com.mnu.sample.service.pds;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.javax.JavaxServletFileUpload;

import com.mnu.sample.service.Action;
//자료실 목록
public class PdsWriteProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서버상의 실제 경로 찾기
		ServletContext context = request.getServletContext();
		String path = context.getRealPath("Pds/upload/");
		//System.out.println("서버상의 실제 경로 : " + path);

		String encType = "UTF-8";
		int sizeLimit = 2 * 1024 * 1024;//최대 2MB
		
		DiskFileItemFactory factory = 
				new DiskFileItemFactory.Builder().setBufferSize(sizeLimit)
												.setCharset(encType)
												.setPath(path)
												.get();		
		//파일 업로드를 처리할 ServletFileUpload 객체
		JavaxServletFileUpload upload = new JavaxServletFileUpload(factory);
		upload.setSizeMax(sizeLimit);
		
		//사용자가 보낸 HTTP(request)를 분석하여
		//요청 안에 들어 있는 모든 폼 데이터와 파일들을 FileItem 객체들로 바꾸어 리스트(List) 형태로 반환
		List items = upload.parseRequest(request);
		//리스트에 담긴 데이터들을 하나씩 꺼내어 확인하기 위해 반복자(Iterator)를 준비
		Iterator iter = items.iterator();
		//while(iter.hasNext()) { ... } 문장을 붙여서 
		//각 아이템이 일반 텍스트 폼 필드인지, 업로드된 파일인지 확인하고 처리
		String name = ((FileItem)iter.next()).getString();
		String email = ((FileItem)iter.next()).getString();
		String subject = ((FileItem)iter.next()).getString();
		String contents = ((FileItem)iter.next()).getString();
		FileItem file = (FileItem)iter.next();
		String filename = file.getName();//경로를 제외한 이름
		String pass = ((FileItem)iter.next()).getString();
		
		//테스트
		System.out.println("이름 : " + name);
		System.out.println("이메일 : " + email);
		System.out.println("제목 : " + subject);
		System.out.println("내용 : " + contents);
		System.out.println("파일명 : " + filename);
		System.out.println("비번 : " + pass);
		
		
		
		
	}

}
