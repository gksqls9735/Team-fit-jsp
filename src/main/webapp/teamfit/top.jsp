<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String loginID = (String) session.getAttribute("loginID");
String memType = null;
if(loginID != null){
    UserDAO topDao = UserDAO.getInstance();
    memType = topDao.getMemType(loginID);
    System.out.println(memType);
    session.setAttribute("memType", memType); // 세션에 설정
}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>TeamFit</title>
<link rel="stylesheet" href="styles_final.css?v=4" />
<script type="text/javascript" src="scripts_final.js?v=1" defer></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js" defer></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body onload="call_js()">
	<header>
		<div>
			<input type="text"
				id="searchbox" size="20" minlength="2" maxlength="15"
				placeholder="Search..." />
		</div>
		<a href="main.jsp"> <img id="logo" src="teamfit_logo.png" alt="로고" />
		</a>
		<ul>
			<li class="menu"><a href="main.jsp">Home</a></li>
			<li class="menu"><a href="#">Sitemap</a></li>
		</ul>
	</header>
	<nav>
		<ul id="navmenu">
			<li class="menu"><a href="#">강의</a>
				<div class="submenu">
					<a href="c_lecture_list.jsp">강의 신청</a>
					<a href="c_lecture_mylist.jsp">강의 신청 목록</a>
					<c:if test="${sessionScope.memType eq 'instructor'}">
          	<a href="create_lecture.jsp">강의 생성</a>
        	</c:if>
				</div></li>
				<li class="menu"><a href="#">강사</a>
				<div class="submenu">
					<a href="instList.jsp">강사 목록</a>
					<c:if test="${sessionScope.memType eq 'instructor'}">
          	<a href="inst_regi.jsp">강사 등록</a>
        	</c:if>
				</div></li>
			<li class="menu"><a href="#">게시판</a>
				<div class="submenu">
					<a href="customer_support_list.jsp" onclick="boardVis()">고객센터</a> <a href="customer_support.jsp">글작성</a>
				</div></li>
		</ul>
		<ul>
		<c:choose>
			<c:when test="${loginID == null}">
				<li class="member" onclick="window.location='login.jsp'"><a
					href="#">로그인</a></li>
				<li class="member" onclick="window.location='signup.jsp'"><a
					href="#" id="signinbut">회원가입</a></li>
			</c:when>
			<c:when test="${loginID eq 'admin1234'}">
				<li class="member" onclick="window.location='logout.jsp'"><a
					href="#">로그아웃</a></li>
				<li class="member" onclick="window.location='memList.jsp'"><a
					href="#" id="signinbut">회원관리</a></li>
			</c:when>
			<c:when test="${loginID != null}">
			<li class="member" onclick="window.location='logout.jsp'"><a
				href="#">로그아웃</a></li>
			<li class="member" onclick="window.location='myPage.jsp'"><a
				href="#" id="signinbut">마이페이지</a></li>
			</c:when>
		</c:choose>
		</ul>
	</nav>