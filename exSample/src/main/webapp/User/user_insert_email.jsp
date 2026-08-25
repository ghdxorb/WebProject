<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<title>회원등록</title>
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
-->
.formbox {
	BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
} 
--->
<link rel="stylesheet" type="text/css" href="/css/stylesheet.css">
</STYLE>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script> 
<script type="text/javascript">
$(function(){
	$("#smscheck").hide();
	$("#emailcheck").hide();
	$("#email").hide(); // 숨기기

	//라디오 버튼 선택시
	$("input[name='mode']").change(function() {
        if ($("#mode1").is(":checked")) {
            $("#phone").show();
            $("#email").hide();
           	$("#smscheck").hide();
           	$("#emailcheck").hide();
        } else {
            $("#email").show();
            $("#phone").hide();
           	$("#smscheck").hide();
           	$("#emailcheck").hide();
        }
    });

	//$("input[name='userid']").on("change",function(){
	$("#userid").on("change",function(){
		var userid=$('#userid').val();
		//alert(userid);
		$.ajax({
			url:'/User?cmd=userIdCheck',
			type:'post',
			data:{'userid':userid},
			success:function(result){
				if(result==0){
					//중복된 아이디가 없는 경우
					userID_c.innerHTML='사용가능한 아이디 입니다';
				}else{
					//ID 중복시
					userID_c.innerHTML='중복된 아이디 입니다';
					$("#userid").val('');
					$("#userid").focus();
				}
			}
		});
	});
	
	//비번 확인
	$("#repasswd").on("change",function(){
		var passwd = $("#passwd").val();
		var repasswd = $("#repasswd").val();
		if(passwd==repasswd){
			repasswd_c.innerHTML="확인되었습니다";
		}else{
			repasswd_c.innerHTML="비밀번호를 확인해 주세요";
			$("#repasswd").val('');
			$("#repasswd").focus();
		}
	});
	
	//유효성 검사
	$("#btn_write").click(function(){
		//이름검사
		if($("#name").val()==''){
			alert("이름을 입력하세요");
			$("#name").focus();
			return;
		}
		//비번
		
		//비번검사
		
		//전화
		
		$("#user").submit();
	
	});
	
	//핸드폰 인증하기 버튼 클릭시
	$("#phoneBtn1").click(function(){
		$("#smscheck").show();

	});
	
	//이메일 인증하기 버튼 클릭시
	$("#emailBtn1").click(function(){
		$("#emailcheck").show();

	});
	
});//$(function()끝
		
</script>
</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 
 <!-- 탑 메뉴 영역 삽입-->
<%@ include file="/Include/topmenu.jsp" %>

<table border="0" width="800">
<tr>
  <td width="20%"  bgcolor="#ecf1ef" valign="top" style="padding-left:0;">
	
	<!--로그인 영역 삽입-->
	<%@ include file="/Include/login_form.jsp" %>
	
  </td>
  <td width="80%" valign="top">&nbsp;<img src="/Images/img/title1.gif" ><br>    
	<form id="user" name="user" method=post action="/User?cmd=userWritePro">
	<table border=0 cellpadding=0 cellspacing=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="/Images/img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자등록(Email 본인인증)&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="/Images/img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text id=name name=name size=16 maxlength=20 value="" placeholder="성명은 빈칸없이 입력하세요.">
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text id=userid name=userid size=12 maxlength=16 value="" style="width:120">
											</td>
											<td id="userID_c">
                  								[ 5~16자 이내의 영문이나 숫자만 가능합니다. ]
                  							</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
								<input type=password id=passwd name=passwd size=8 maxlength=12 style="width:80">
									6~12자 이내의 영문이나 숫자만 가능합니다.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password id=repasswd name=repasswd size=8 maxlength=12 value="" style="width:80">
									<font id=repasswd_c color=red>&nbsp;*비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요. </font> 
								</td>
							</tr>
							<tr id="phone">
								<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text id=tel name=tel size=13 maxlength=13 value="" placeholder="휴대전화번호 (-제외)">
									<input type="button" id="phoneBtn1" value="인증번호받기">
									<font id="phone_c" size="2" color="red">&nbsp;</font>
								</td>
							</tr>
							<tr id="smscheck">
								<TD BGCOLOR="#EFF4F8">&nbsp;인증번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text id=resms name="resms" size=13 maxlength=13 placeholder="인증번호를 입력하세요">
									<input type="button" id="phoneBtn2" value="재발송">
                    				<font id="resms_r" size="2" color="red">&nbsp;</font>
                    				<input type="button" value="인증" id="phoneBtn3">
                    				<font id="resms_c" size="2" color="red">&nbsp;</font>
								</td>
							</tr>							
							<tr id="email">
								<TD BGCOLOR="#EFF4F8">&nbsp;E-mail
                					<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type="text" name="email1" size=13 maxlength="15">
									@ <input type="text" name="email2" size=13 maxlength="15">
									<select name="email3">
		      							<option value="0">직접입력</option>
		      							<option value="naver.com">naver.com</option>
		      							<option value="daum.net">daum.net</option>
		      							<option value="nate.com">nate.com</option>
		      							<option value="gmail.com">gmail.com</option>
		  							   </select>
									 <input type="button" id="emailBtn1" value="인증하기">
								</td>
							</tr>
							<tr id="emailcheck">
								<TD BGCOLOR="#EFF4F8">&nbsp;이메일 인증번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text id=reemail name="reemail" size=13 maxlength=13 placeholder="이메일 인증번호를 입력하세요">
									<input type="button" id="emailBtn2" value="재발송">
                    				<font id="reemail_r" size="2" color="red">&nbsp;</font>
                    				<input type="button" value="인증" id="emailBtn3">
                    				<font id="reemail_c" size="2" color="red">&nbsp;</font>
								</td>
							</tr>							
							
						</table>
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="/Images/img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center></td>
								<td valign=bottom>
									<img src="/Images/img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<img src="/Images/img/u_bt06.gif" vspace=3 border=0 id="btn_write">
									<img src="/Images/img/u_bt05.gif" border=0 hspace=10 vspace=3 id="btn_cancle" name=img4>
								</td>
							</tr>
						</table> 
					</td>
				</tr>
				</td>
			</tr>
		</table>
	</form>
	</td>
</tr>
</table>

 <!-- copyright 영역 삽입-->
  

</body>
</html>
