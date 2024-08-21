<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserDAO dao = UserDAO.getInstance();
String memberID = request.getParameter("memberID");
boolean check = dao.idCheck(memberID);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 체크</title>
<script type="text/javascript" src="mysignin.js"></script>
<style>
.center {
	text-align: center;
}
</style>
</head>
<body class="center">
	<br>
	<div class="center" >
        <b><%= memberID %></b>
        <%
        if (check) {
            out.println("는 이미 존재하는 ID입니다.<br></br>");
        %>
        <button onClick="closeWindow(1)">닫기</button>
        <a href="#" onClick="closeWindow(1)">닫기</a>
        <%
        } else {
            out.println("는 사용 가능합니다.<br></br>");
        %>
        <a href="#" onClick="closeWindow(2)">닫기</a>
        <%
        }
        %>
    </div>
		<%--
		<a href="#" onClick="javascript:opener.document.regForm.id.value=''; self.close()">닫기</a> id를 지워준다
		함수로 활용가능 각각 onClick에 javascript:closeWindow(flag)로 준다
		이걸 이미 존재하는 id에 넣어주기 self.close()는 사용가능합니다에 넣어주기
		 --%>
</body>
</html>