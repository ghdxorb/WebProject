<%@ page contentType="text/html; charset=UTF-8" %>


<%@ include file="/Include/topmenu.jsp" %>

<html>
<head><title>자료 등록 수정</title><link rel="stylesheet" type="text/css" href="/stylesheet.css">
<script type="text/javascript">
	function check(){
			if(pds.name.value==""){
   				alert("작성자이름을 입력하세요");
   				pds.name.focus();
   				return;
   			}
   			if(pds.subject.value==""){
   				alert("제목을 입력하세요");
   				pds.subject.focus();
   				return;
   			}
   			if(pds.contents.value==""){
   				alert("내용을 입력하세요");
   				pds.contents.focus();
   				return;
   			}
   			if(!pds.pass.value){
   				alert("비밀번호을 입력하세요");
   				pds.pass.focus();
   				return;
   			}
   			//파일용량제한
   			if(pds.filename.value){//파일 선택시
   				var size = pds.filename.files[0].size;
   				if(size>(2*1024*1024)){ // 파일용량이 2M 초과시
   					alert("파일용량은 2M을 초과할수 없습니다.");
   					pds.filename.focus()
   					return;
   				}
   			}
   			
   			pds.submit();
   		}

	function back(){
		history.back();
	}

</script>

</head>
<body topmargin="0" leftmargin="0">
<table border="0" width="800">
<tr>
  <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">

  <!--  로그인 폼 추가부분 -->
	<jsp:include page="/Include/login_form.jsp" /> 

  
  </td>
  <td width="80%" valign="top">&nbsp;<br>
  <img src="/Images/img/bullet-01.gif"><font size="3" face="돋움" color="blue">
  <b> 뮤직자료실</b></font><font size="2"> - 자료 수정하기</font><p>
  <img src="/Images/img/bullet-03.gif"><font size="2" face="돋움" color="orange"> 잠깐</font> &nbsp;
  <img src="/Images/img/bullet-02.gif"><font size="2" face="돋움">는 필수 입력 사항입니다.</font><p>
     <form name="pds" method="post" action="/Pds?cmd=pdsModifyPro" enctype="multipart/form-data">
		<input type="hidden" name="idx" value="${pDTO.idx}">
		<input type="hidden" name="filename1" value="${pDTO.filename}">

   <table border="0">
    <tr>
      <td width="5%" align="right"><img src="/Images/img/bullet-02.gif"></td>
      <td width="15%"><font size="2" face="돋움">글쓴이</font></td>
      <td width="80%"><input type="text" size="20" name="name" value="${pDTO.name }" readonly></td></tr>
    <tr>
      <td align="right">&nbsp;</td>
      <td ><font size="2" face="돋움">메일주소</font></td>
      <td><input type="text" size="20" name="email" value="${pDTO.email}"></td></tr>
    <tr>
      <td align="right"><img src="/Images/img/bullet-02.gif"></td>
      <td><font size="2" face="돋움">제목</font></td>
      <td><input type="text" size="60" name="subject" value="${pDTO.subject}"></td></tr>
    <tr>
      <td align="right"><img src="/Images/img/bullet-02.gif"></td>
      <td><font size="2" face="돋움">자료설명</font></td>
      <td><textarea wrap="physical" rows="10" name="contents" cols="60">${pDTO.contents }</textarea></td></tr>
    <tr>
      <td align="right"><img src="/Images/img/bullet-02.gif"></td>
      <td><font size="2" face="돋움">파일첨부</font></td>
      <td><input type="file" name="filename" size="30" ><font size="2" face="돋움">${pds.filename}</font></td></tr>
    <tr>
      <td align="right"><img src="/Images/img/bullet-02.gif"></td>
      <td><font size="2" face="돋움">비밀번호</font></td>
      <td><input type="password" size="10" name="pass"></td></tr>
    <tr>
      <td align="right">&nbsp;</td>
      <td><font size="2">&nbsp;</font></td>
      <td><input type="button" value="수정하기" onClick="check()">&nbsp;
      <input type="button" value="돌아가기" onClick="back()"></td></tr>
    </table>
    </form>
    </td>
  </tr>
  </table>
</body>
</html>
