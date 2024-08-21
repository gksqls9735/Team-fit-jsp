<%@page import="controller.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
String loginID = (String) session.getAttribute("loginID");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
String pass = request.getParameter("pass");
CustomerDAO dao = CustomerDAO.getInstance();
int check = dao.deleteArticle(num, loginID);
if (check == 1) {
%>
<meta http-equiv="Refresh" content="0;url=customer_support_list.jsp?pageNum=<%=pageNum%>">
<%
} else if (check == 0) {
%>
<script language="JavaScript">
	alert("아이디가 맞지 않습니다");
	history.go(-1);
</script>
<%
} else {
%>
<script language="JavaScript">
	alert("데이터베이스 오류");
	history.go(-1);
</script>
<%
}
%>
