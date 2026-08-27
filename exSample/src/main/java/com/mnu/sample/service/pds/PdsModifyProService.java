package com.mnu.sample.service.pds;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.javax.JavaxServletFileUpload;

import com.mnu.sample.model.PdsDAO;
import com.mnu.sample.model.PdsDTO;
import com.mnu.sample.service.Action;
//자료실 목록
public class PdsModifyProService implements Action {

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = request.getServletContext();
		String path = context.getRealPath("Pds/upload/");
		//System.out.println("서버상의 실제 경로 : " + path);//
		
		String encType = "UTF-8";
		int sizeLimit = 2 * 1024 * 1024;//최대 2MB
		
		DiskFileItemFactory factory = 
				new DiskFileItemFactory.Builder().setBufferSize(sizeLimit).setCharset(encType).setPath(path).get();		
		
		JavaxServletFileUpload upload = new JavaxServletFileUpload(factory);
		upload.setSizeMax(sizeLimit);

		List items = upload.parseRequest(request);
		Iterator iter = items.iterator();

		int idx = Integer.parseInt(((FileItem)iter.next()).getString());
		String filename1 = ((FileItem)iter.next()).getString();

		String name = ((FileItem)iter.next()).getString();
		String email = ((FileItem)iter.next()).getString();
		String subject= ((FileItem)iter.next()).getString();
		String contents = ((FileItem)iter.next()).getString();
		FileItem file = (FileItem)iter.next();
		String filename = file.getName();
		String pass = ((FileItem)iter.next()).getString();
/*		
		System.out.println("idx: " + idx);
		System.out.println("page: " + nowpage);
		System.out.println("filename1 : " + filename1);
		System.out.println("이름: " + name);
		System.out.println("이메일: " + email);
		System.out.println("제목: " + subject);
		System.out.println("내용: " + contents);
		System.out.println("파일명: " + filename);
		System.out.println("비번: " + pass);
*/		
		//파일저장
		if(filename != null && !filename.equals("")) {
			Path path2 = Paths.get(path+filename);
			file.write(path2) ;
		}

		PdsDTO pDTO = new PdsDTO();
		pDTO.setIdx(idx);
		pDTO.setName(name);
		pDTO.setEmail(email);
		pDTO.setSubject(subject);
		pDTO.setContents(contents);
		pDTO.setFilename(filename);
		pDTO.setPass(pass);

		
		if(filename == null || filename.equals("")) {  // 예전 파일 이용시
			pDTO.setFilename(filename1);
		}else{	//새 파일로 변경(구파일삭제)
			File a1 = new File(path + filename1);
			if (a1.exists())
				a1.delete();
		}

		PdsDAO pDAO = PdsDAO.getInstance();
		int row = pDAO.pdsModify(pDTO);

		request.setAttribute("row", row);

		RequestDispatcher rd = request.getRequestDispatcher("/Pds/pds_modify_pro.jsp");
		rd.forward(request, response);

	}
}
