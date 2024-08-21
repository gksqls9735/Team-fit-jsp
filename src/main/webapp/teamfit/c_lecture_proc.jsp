<%@page import="controller.LectureDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%
    String dateTimeStr = request.getParameter("date_time");
    Timestamp dateTime = null;
    if (dateTimeStr != null) {
        dateTime = Timestamp.valueOf(dateTimeStr.replace("T", " ") + ":00"); // 형식이 "2024-05-31T08:56"인 경우
    }
    request.setAttribute("date_time", dateTime);
%>
<%
request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" scope="page" class="model.LectureVO">
	<jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
System.out.println(vo.toString());
LectureDAO dao = LectureDAO.getInstance();
int flag = dao.insertLecture(vo);
if (flag == 1) {
	response.sendRedirect("main.jsp");
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