<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
UserDAO dao = UserDAO.getInstance();
String memberID = request.getParameter("memberID");
String memberPW = request.getParameter("memberPW");
int check = dao.loginCheck(memberID, memberPW);
%>
<%
if (check == 1) {//로그인 성공
	session.setAttribute("loginID", memberID);
	response.sendRedirect("main.jsp");	
} else if (check == 0) {//아이디는 있는데 비밀번호 오류
%>
<script>
	alert("비밀번호가 틀렸습니다");
	history.go(-1);
</script>
<%
} else {//아이디 자체가 존재하지 않는 경우
%>
<script>
	alert("아이디가 존재하지 않습니다");
	history.go(-1);
</script>
<%
}
%>