<%@page import="controller.InstMatchDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
String loginID = (String) session.getAttribute("loginID");
InstMatchDAO dao = InstMatchDAO.getInstance();
int check = dao.cancleMatch(loginID);
String msg;
String redirectURL;
if (check == 1) {
    msg = "매칭 취소 성공";
    redirectURL = "main.jsp";
} else {
    msg = "매칭 취소 실패";
    redirectURL = "javascript:history.go(-1);";
}
%>
<script>
    alert('<%= msg %>');
    window.location.href = '<%= redirectURL %>';
</script>
