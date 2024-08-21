<%@page import="controller.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String[] memberIDs = request.getParameterValues("delete");
	UserDAO dao = UserDAO.getInstance();
	int count = dao.deleteAllMembers(memberIDs);
%>
<%
	if(count == -1){
%>
	<script type="text/javascript">
		alert("사용자를 선택하지 않았습니다.");
	</script>
	<meta http-equiv="Refresh" content="0;url=memList.jsp">
<%
	} else {
%>
	<script type="text/javascript">
		alert("<%= count %> 명의 사용자를 삭제하였습니다.");
	</script>
	<meta http-equiv="Refresh" content="0;url=memList.jsp">
<%
	}
%>