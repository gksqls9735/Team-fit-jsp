<%@page import="controller.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" scope="page" class="model.CustomerVO">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
String pageNum = request.getParameter("pageNum");
CustomerDAO dao = CustomerDAO.getInstance();
System.out.println("업데이트 프로시저"+pageNum);
int check = dao.updateArticle(vo);
if (check == 1) {
%>
<meta http-equiv="Refresh" content="0;url=customer_support_list.jsp?pageNum=<%=pageNum%>">
<%
} else if (check == 0){
%>
<script>
	alert("수정에 실패하였습니다.");
</script>
	<meta http-equiv="Refresh" content="0;url=customer_support_list.jsp?pageNum=<%=pageNum%>">
<%
} else {
%>
<script>
	alert("데이터베이스 오류입니다.");
	history.go(-1);
</script>
<%}
%>