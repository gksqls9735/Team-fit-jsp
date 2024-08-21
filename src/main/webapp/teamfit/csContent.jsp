<%@page import="model.CustomerVO"%>
<%@page import="controller.CustomerDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
System.out.println("글목록"+pageNum);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
try {
	CustomerDAO dao = CustomerDAO.getInstance();
	CustomerVO vo = dao.getArticle(num);
	int ref = vo.getRef();
	int step = vo.getStep();
	int depth = vo.getDepth();
%>
<main>
    <form class="form-container2 view-form">
        <h2>글 보기</h2>
        <div class="table-header">
            <input type="button" value="글목록" class="list-btn" onclick="document.location.href='customer_support_list.jsp?pageNum=<%=pageNum%>'">
        </div>
        <table class="view-table">
            <tr>
                <th>글번호</th>
                <td><%= vo.getNum() %></td>
            </tr>
            <tr>
                <th>조회수</th>
                <td><%= vo.getReadcount() %></td>
            </tr>
            <tr>
                <th>작성자 ID</th>
                <td><%= vo.getMemberID() %></td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><%= sdf.format(vo.getCreatedDate()) %></td>
            </tr>
            <tr>
                <th>글제목</th>
                <td><%= vo.getTitle() %></td>
            </tr>
            <tr>
                <th>글내용</th>
                <td>
                <textarea readonly class="content-textarea"><%= vo.getMessage() %></textarea>
                </td>
            </tr>
        </table>
        <div class="button-group">
            <input type="button" value="답변달기" onclick="document.location.href='customer_support.jsp?num=<%=vo.getNum()%>&ref=<%=vo.getRef()%>&step=<%=vo.getStep()%>&depth=<%=vo.getDepth()%>'">
            <input type="button" value="글삭제" onclick="document.location.href='csDelete.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
            <input type="button" value="글수정" onclick="document.location.href='csUpdate.jsp?num=<%=vo.getNum()%>&pageNum=<%=pageNum%>'">
        </div>
    </form>
</main>
<%
} catch (Exception e) {
	e.printStackTrace();
}
%>
<%@ include file="bottom.jsp"%>