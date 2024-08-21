<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserDAO dao = UserDAO.getInstance();
String memberID = request.getParameter("memberID");
String memberName = request.getParameter("memberName");
String phoneNum = request.getParameter("phoneNum");
String memberPW = dao.pwSearch(memberID, memberName, phoneNum);
%>
<%@ include file="top.jsp"%>
<main>
	<div class="signup idsearch">
		<form action="login.jsp" method="post">
			<div style="text-align: center;">
				<%
				if (memberPW == null) {
					out.println(memberID + "은/는 존재하지 않는 회원이거나 정보가 일치하지 않습니다.<br></br>");
				} else {
					out.println(memberID + "님의 비밀번호는 " + memberPW + "입니다.<br></br>");
				}
				%>
			</div>
			<input id="login" type="submit" value="로그인"> <input type="button"
				id="pwSearch" value="뒤로가기" onclick="window.location='myPwSearch.jsp'">
		</form>
	</div>
</main>
<%@ include file="bottom.jsp"%>