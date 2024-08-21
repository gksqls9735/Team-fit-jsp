<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// 게시판 답글을 작성할 때 사용 (원글 num = 0, ref = 1, step = 0, depth = 0/답글 원글 바탕하에 변수가 결정)
int num = 0, ref = 1, step = 0, depth = 0;
try {
	if (request.getParameter("num") != null) {
		num = Integer.parseInt(request.getParameter("num"));
		ref = Integer.parseInt(request.getParameter("ref"));
		step = Integer.parseInt(request.getParameter("step"));
		depth = Integer.parseInt(request.getParameter("depth"));
	}
%>
<main>
	<c:if test="${loginID == null}">
		<script type="text/javascript">
			alert("로그인을 먼저 진행해주세요.");
			setTimeout(function() {
				window.location.href = 'login.jsp';
			}, 1000);
		</script>
	</c:if>
	<div class="form-container support-form">
		<h2>자유게시판</h2>
		<form action="customer_support_proc.jsp" method="POST"
			name="writeForm">
			<input type="hidden" name="num" value="<%=num%>"> 
			<input type="hidden" name="ref" value="<%=ref%>"> 
			<input type="hidden" name="step" value="<%=step%>"> 
			<input type="hidden" name="depth" value="<%=depth%>">
			<div class="form-group">
				<label for="memberID">아이디</label> <input type="text" id="memberID"
					name="memberID" required value="<%=loginID%>" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="type">문의 유형</label> <select id="type" name="type"
					required>
					<option value="일반">일반 문의</option>
					<option value="강의">강의 관련 문의</option>
					<option value="강사">강사 관련 문의</option>
					<option value="결제">결제 문의</option>
					<option value="기타">기타</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label> 
				<%
					if (request.getParameter("num") == null) { //답변인지 아닌지 확인
					%> <input type="text" id="title" name="title" required/> <%
				 } else {
				 %>
				 <input type="text" id="title" name="title" value="[답변]"/>
				 <%
				 }
				 %>
			</div>
			<div class="form-group">
				<label for="message">내용</label>
				<textarea id="message" name="message" rows="10" required></textarea>
			</div>
			<div class="supp_in">
			<input type="submit" value="문의하기" /> <input type="reset" value="다시작성" />
			<input type="button"
				onclick="javascript:window.location='customer_support_list.jsp'"
				value="글목록" />
			</div>
		</form>
	</div>
</main>
<%
	} catch (Exception e) {
	e.printStackTrace();
	}
%>
<%@ include file="bottom.jsp"%>