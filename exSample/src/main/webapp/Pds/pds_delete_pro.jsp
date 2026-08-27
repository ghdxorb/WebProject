<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${row==1}">

		   <script language="javascript">
     		//window.opener.location.replace("pds_list?page"+${page});
     		window.opener.location.replace("/Pds?cmd=pdsList");
     		self.close();
   		   </script>
</c:if>
<c:if test="${row==0}">
		    <script language="javascript">
     			alert("비밀번호가 맞지 않습니다.\n\n글 작성시의 비밀번호를 입력해 주세요.");
     			history.back();
   			</script>
</c:if>
</body>
</html>