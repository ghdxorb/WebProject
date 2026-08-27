<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/Include/topmenu.jsp" %>

<html>
   <head><title> 자료 올리기 </title>
   <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   
<script>
	$(document).ready(function() {	
		$("#btn_send").click(function(){
			
			if($("#name").val()==''){
				alert("이름을 입력하세요");
				$("#name").focus();
				return;
			}
			if($("#subject").val()==''){
				alert("제목을 입력하세요");
				$("#subject").focus();
				return;
			}
			if($("#contents").val()==''){
				alert("내용을 입력하세요");
				$("#contents").focus();
				return;
			}
			//파일 선택시
			if($("#filename").val()){
				var size = $("#filename")[0].files[0].size;
				if(size>(2*1024*1024)){
					alert("파일용량은 2M을 초과할수 없습니다");
					$("#filename").focus();
				}
			}
			
			if($("#pass").val()==''){
				alert("비밀번호을 입력하세요");
				$("#pass").focus();
				return;
			}
			
			$("#pds").submit();
		});
		
		$("#btn_cancle").click(function(){
			history.back();
		});

	});

</script>

</head> 
<body>
<link rel="stylesheet" type="text/css" href="stylesheet.css">

<table border="0" width="800">
 <tr>
   <td width="20%" height="500" bgcolor="#ecf1ef" valign="top">

	<!--     다음에 추가할 로그인  -->
	<jsp:include page="/Include/login_form.jsp" /> 

   </td>
   <td width="80%" valign="top">&nbsp;<br>
     <img src="/Images/img/bullet-01.gif"><font size="3" face="돋움" color="blue"> <b>참 좋은 자료실</b></font>
     <font size="2"> - 자료올리기</font><p>
     <img src="/Images/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
     <img src="/Images/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
	 <form id="pds" name="pds" method="post" enctype="multipart/form-data" action="/Pds?cmd=pdsWritePro">
	  <table border="0" >
		<tr>
         <td width="5%" align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
         <td width="80%">
			<input type="text" size="20" id="name" name="name"></td>
		</tr>
		<tr> 
		  <td align="right">&nbsp;</td>
          <td><font size="2" face="돋움">메일주소</font></td>
		  <td><input type="text" size="20" id="email" name="email"></td>
		</tr>	
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">제목</font></td>
         <td><input type="text" size="60" id="subject" name="subject"></td>
       </tr>
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">내용</font></td>
         <td><textarea wrap="physical" rows="10" id="contents" name="contents" cols="60"></textarea></td>
       </tr>
		<tr>
		  <td align="right"><img src="/Images/img/bullet-02.gif"></td>
		  <td><font size="2" face="돋움">파일첨부</font></td>
		  <td><input type="file" id="filename" name="filename" size="30"></td></tr>
		<tr>
       <tr>
         <td align="right"><img src="/Images/img/bullet-02.gif"></td>
         <td><font size="2" face="돋움">비밀번호</font></td>
          <td><input type="password" size="10" id="pass" name="pass" > 
          <font size="2" face="돋움">*.게시글의 수정 및 삭제시 필요.</font>
         </td>
        </tr>

		<tr></tr>			<tr></tr>
          <td align="right">&nbsp;</td>
          <td><font size="2">&nbsp;</font></td>
          <td align=center>
			<img src="/Images/img/save.gif" width="56" height="18" border="0" id="btn_send">
			<img src="/Images/img/cancle.gif" width="56" height="18" border="0" id="btn_cancle">

		  </td>
        </tr>
      </table>
      </form>
    </td>
  </tr>
 </table>
</body>		
</html>
