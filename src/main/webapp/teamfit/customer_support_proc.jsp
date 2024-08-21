<%@page import="controller.CustomerDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" scope="page" class="model.CustomerVO">
	<jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
vo.setCreatedDate(new Timestamp(System.currentTimeMillis()));
System.out.println(vo.toString());
CustomerDAO dao = CustomerDAO.getInstance();
boolean flag = dao.insertArticle(vo);
if (flag) {
	response.sendRedirect("customer_support_list.jsp");
} else {
	String msg = "입력 실패";
	//ont.print(msg); 도 alert 대신 사용 가능
%>
<script>
	alert(<%= msg %>);
	history.go(-1);
</script>
<%
}
%>

