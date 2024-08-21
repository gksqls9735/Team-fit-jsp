<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<script type="text/javascript">
	function begin() {
		document.myForm.pass.focus();
	}
	function checkIt() {
		if (!document.myForm.pass.value) {
			alert("비밀번호를 입력하지 않았습니다");
			document.myForm.pass.focus();
			return false;
		}
		return true;
	}
</script>
<main>
	<div class="signup idsearch">
		<form style="text-align: center;" action="deleteProc.jsp" method="post" name="myForm" onsubmit="return checkIt()">
			<label for="memberPW">비밀번호 입력</label> 
			<input type="password" id="memberPW" name="memberPW" required style="margin-bottom: 20px; height: 25px;">
			<input id="login" type="submit" value="회원탈퇴"> <input
				id="pwSearch" type="button" value="취소"
				onclick="javascript:window.location='main.jsp'">
		</form>
	</div>
</main>
<%@ include file="bottom.jsp"%>