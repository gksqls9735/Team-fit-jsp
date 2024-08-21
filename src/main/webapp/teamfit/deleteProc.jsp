<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserDAO dao = UserDAO.getInstance();
String id = (String) session.getAttribute("loginID");
String pass = request.getParameter("memberPW");
int check = dao.deleteMember(id, pass);
%>
<%
if (check == 1) {
	session.invalidate();
	response.sendRedirect("main.jsp");
} else {
%>
<script type="text/javascript">
	alert("비밀번호가 맞지 않습니다.");
	history.go(-1);
</script>
<%
}
%>