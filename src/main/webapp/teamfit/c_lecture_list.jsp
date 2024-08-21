<%@page import="java.util.ArrayList"%>
<%@page import="model.LectureVO"%>
<%@page import="java.util.List"%>
<%@page import="controller.LectureDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
LectureDAO dao = LectureDAO.getInstance();
ArrayList<LectureVO> lectureList = dao.getLecture();
if(loginID == null){
	String msg = "로그인 후 진행해주세요.";
%>
<script>
	alert('<%= msg %>');
</script>
<%
response.sendRedirect("login.jsp");
}
%>	
<main>
<div class="support-list-container">
    <form action="lecture_join_proc.jsp" method="post">
    <input type="hidden" name="memberID" value="<%=loginID%>">
        <table class="cs_list">
        <thead>
            <tr>
                <th>강의 코드</th>
                <th>강사 아이디</th>
                <th>강의 제목</th>
                <th>강의 유형</th>
                <th>강의 날짜</th>
                <th>강의 장소</th>
                <th>강의 난이도</th>
                <th>참가 비용</th>
                <th>정원</th>
                <th>신청</th>
            </tr>
        </thead>
            <%
            for (LectureVO data : lectureList) {              
            %>
            <tbody id="question-list">
            <tr>
                <td><%=data.getCode()%></td>
                <td><%=data.getInstructorId()%></td>
                <td><%=data.getTitle()%></td>
                <td><%=data.getL_type()%></td>
                <td><%=data.getDate_time()%></td>
                <td><%=data.getL_location()%></td>
                <td><%=data.getL_level()%></td>
                <td><%=data.getFee()%></td>
                <td><%=data.getApplicants()%>/<%=data.getL_capacity() %></td>
                <td>
                    <button type="submit" name="code" value="<%=data.getCode()%>">신청</button>
                </td>          			
            </tr>
            <%
            }
            %>
            </tbody>
        </table>
    </form>
</div>
</main>
<%@ include file="bottom.jsp"%>
