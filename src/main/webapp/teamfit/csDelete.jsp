<%@page import="model.CustomerVO"%>
<%@page import="controller.CustomerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
try{
	CustomerDAO dao = CustomerDAO.getInstance();
	CustomerVO vo = dao.updateGet(num);
%>
<main>
<c:if test="${loginID eq vo.getMemberID()}">
		<script type="text/javascript">
			alert("해당 글의 작성자만 삭제가 가능합니다.");
		</script>
		<meta http-equiv="Refresh" content="1;url=customer_support_list.jsp?pageNum=<%=pageNum%>">
	</c:if>
	<div class="signup idsearch">
		<form action="csDeleteProc.jsp?pageNum=<%=pageNum%>" method="post">
		<input type="hidden" name="num" value="<%=num%>">
			<div style="text-align: center;">
				글을 삭제하시겠습니까?
			</div>
			<div class="button-group button-group2">
         <input type="submit" value="예" class="submit-btn" style="width: 85px;"/>
         <input type="button" value="아니오" style="padding: 14px 21px 14px 21px" onclick="window.history.back()"/>
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