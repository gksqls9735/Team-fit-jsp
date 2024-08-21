<%@page import="controller.InstructorsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" scope="page" class="model.InstructorsVO">
    <jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
InstructorsDAO dao = InstructorsDAO.getInstance();
int check = dao.insertInst(vo);

if(check == 1){
	String msg = "강사 정보 등록 완료";
%>
	<script>
  	alert('<%= msg %>');
  </script>
<%
	response.sendRedirect("instList.jsp");
} else {
    String msg = "입력 실패";
%>
	<script>
  	alert('<%= msg %>');
    history.go(-1);
  </script>
<%
    }
%>
