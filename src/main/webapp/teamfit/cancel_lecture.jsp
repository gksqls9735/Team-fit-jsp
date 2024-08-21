<%@page import="controller.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
int no = Integer.parseInt(request.getParameter("no"));
CartDAO dao = CartDAO.getInstance();
int flag= dao.cancelCart(no);
if(flag == 1){
	String msg = "취소 완료";
%>
	<script>
		alert('<%= msg %>');
	</script>
<%	
	response.sendRedirect("c_lecture_mylist.jsp");
} else {
	String msg = "취소 실패";
%>
	<script>
		alert('<%= msg %>');
    history.go(-1);
	</script>
<%}
%>
