<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="vo" class="model.UserVO">
<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>    
<%
request.setCharacterEncoding("utf-8");
UserDAO dao = UserDAO.getInstance();
String loginID = (String) session.getAttribute("loginID");
vo.setMemberID(loginID);
int count = dao.updateMember(vo);
%>
<%
if (count != 1) {
%>
<script type="text/javascript">
	alert("수정이 실패하였습니다.");
	history.go(-1);
</script>
<%
} else {
%>
<script type="text/javascript">
	alert("수정이 성공하였습니다.");
</script>
<%
response.sendRedirect("main.jsp");
}
%>