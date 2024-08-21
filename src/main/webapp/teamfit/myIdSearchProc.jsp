<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserDAO dao = UserDAO.getInstance();
String memberName = request.getParameter("memberName");
String phoneNum = request.getParameter("phoneNum");
String memberID = dao.idSearch(memberName, phoneNum);
%>
<%@ include file="top.jsp"%>
<main>
	<div class="signup idsearch">
		<form action="login.jsp" method="post">
			<div style="text-align: center;">
				<%
				if (memberID == null) {
					out.println(memberName + "은/는 존재하지 않는 회원이거나 정보가 일치하지 않습니다.<br></br>");
				} else {
					out.println(memberName + "님의 아이디는 " + memberID + "입니다.<br></br>");
				}
				%>
			</div>
			<input id="login" type="submit" value="로그인"> <input
				id="pwSearch" type="button" value="비밀번호 찾기"
				onclick="window.location='myPwSearch.jsp'">
		</form>
	</div>
</main>
<%@ include file="bottom.jsp"%>
