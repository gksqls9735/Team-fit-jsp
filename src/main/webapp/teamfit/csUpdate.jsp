<%@page import="model.CustomerVO"%>
<%@page import="controller.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	System.out.println("업데이트 폼"+pageNum);
	try{
		CustomerDAO dao = CustomerDAO.getInstance();
		CustomerVO vo = dao.updateGet(num);
%>
<main>
	<c:if test="${loginID eq vo.getMemberID()}">
		<script type="text/javascript">
			alert("해당 글의 작성자만 수정이 가능합니다..");
		</script>
		<meta http-equiv="Refresh" content="1;url=customer_support_list.jsp?pageNum=<%=pageNum%>">
	</c:if>
	<div class="form-container support-form">
		<h2>고객센터</h2>
		<form action="csUpdateProc.jsp" method="POST"
			name="writeForm">
			<input type="hidden" name="num" value="<%=num%>"> 
			<input type="hidden" name="pageNum" value="<%=pageNum%>">
			<div class="form-group">
				<label for="memberID">아이디</label> <input type="text" id="memberID"
					name="memberID" required value="<%=loginID%>" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="type">문의 유형</label> <select id="type" name="type"
					required>
					<option value="general_q" <c:if test="${vo.getType() eq 'general_q'}">selected</c:if>>일반 문의</option>
					<option value="lecture_q" <c:if test="${vo.getType() eq 'lecture_q'}">selected</c:if>>강의 관련 문의</option>
					<option value="instructor_q" <c:if test="${vo.getType() eq 'instructor_q'}">selected</c:if>>강사 관련 문의</option>
					<option value="billing" <c:if test="${vo.getType() eq 'billing'}">selected</c:if>>결제 문의</option>
					<option value="etc" <c:if test="${vo.getType() eq 'etc'}">selected</c:if>>기타</option>
				</select>
			</div>
			<div class="form-group">
				<label for="title">제목</label> 
				 <input type="text" id="title" name="title" value="<%= vo.getTitle()%>"/>
			</div>
			<div class="form-group">
				<label for="message">내용</label>
				<textarea id="message" name="message" rows="10"><%= vo.getMessage()%></textarea>
			</div>
			<div class="button-group">
				<input type="submit" value="수정하기" /> 
				<input type="reset" value="다시작성" />
				<input type="button"
	      	onclick="javascript:window.location='customer_support_list.jsp?pageNum=<%=pageNum%>'"
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