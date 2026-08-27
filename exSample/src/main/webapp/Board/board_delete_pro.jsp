<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<c:if test="${row==1}">
	<script>
		//opener.location.href="/Pds?cmd=boardList";//객체
		//새로운 페이지로 이동
		opener.location.replace("/Pds?cmd=pdsList");//메소드
		//기존페이지를 새로운페이지로변경
		self.close();
	</script>
</c:if>

<c:if test="${row==0}">
	<script>
		alert("비밀번호가 맞지 않습니다");
		history.back();
	</script>
</c:if>
