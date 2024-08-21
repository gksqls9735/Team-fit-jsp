<%@page import="controller.InstMatchDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" scope="page" class="model.InstMatchVO">
	<jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
InstMatchDAO matchDao = InstMatchDAO.getInstance();
int flag1 = matchDao.checkMatch(vo.getMemberID());
System.out.println(vo.toString());
if(flag1 >= 1){
	String msg = "이미 매칭된 강사가 존재합니다.";
%>
	<script>
		alert('<%= msg %>');
		history.go(-1);
	</script>
<%
} else {
	int flag2 = matchDao.insertMatch(vo);
	if(flag2 == 1){
		String msg = "매칭 성공";
%>
	<script>
		alert('<%= msg %>');
	</script>
<%
response.sendRedirect("myPage.jsp");
	} else {
		String msg = "매칭 실패";		
%>
<script>
	alert('<%= msg %>');
	history.go(-1);
</script>
<%
	}
%>
<%
}
%>