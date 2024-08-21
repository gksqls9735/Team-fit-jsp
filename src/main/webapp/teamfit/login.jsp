<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>
	<section class="signup">
		<h3>Sign Up</h3>
		<form action="loginProc.jsp" method="post">
			<label for="memberID">아이디:</label> <input type="text" id="memberID"
				name="memberID" required /> <label for="memberPW">비밀번호:</label> <input
				type="password" id="memberPW" name="memberPW" required />
			<input class="loginbutton" type="submit" value="로그인" />
			<div style="margin-top: 10px; text-align: center" >
				<input type="button" value="회원가입" style="margin-right: 10px"
					onClick="javascript:window.location='signup.jsp'"> <input
					type="button" value="아이디 찾기" style="margin-right: 10px"
					onClick="javascript:window.location='myIdSearch.jsp'"> <input
					type="button" value="비밀번호 찾기"
					onClick="javascript:window.location='myPwSearch.jsp'">
			</div>
		</form>
	</section>
</main>
<%@ include file="bottom.jsp"%>