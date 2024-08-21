<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" class="model.UserVO" />
<jsp:setProperty property="*" name="vo" />
<%
UserDAO dao = UserDAO.getInstance();
boolean flag = dao.memberInsert(vo);
System.out.println(vo.toString());
%>
<c:if test="${flag == true}">
	alert("회원가입 성공, 메인페이지로 돌아갑니다.");
</c:if>
<c:if test="${flag == false}">
	alert("회원가입 실패, 메인페이지로 돌아갑니다.");
</c:if>
<%
response.sendRedirect("main.jsp");
%>