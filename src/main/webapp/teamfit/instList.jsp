<%@page import="model.InstJoinData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.InstructorsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
InstructorsDAO dao = InstructorsDAO.getInstance();
ArrayList<InstJoinData> joinList = dao.getInstList();
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
    <form action="inst_match_proc.jsp" method="post">
    <input type="hidden" name="memberID" value="<%=loginID%>">
    <input type="hidden" name="no" value="<%=0%>">
        <table class="cs_list">
        <thead>
            <tr>
                <th>강사 이름</th>
                <th>전공 운동</th>
                <th>소개</th>
                <th>경력</th>
                <th>자격증</th>
                <th>활동 지역</th>
                <th>신청</th>
            </tr>
        </thead>
            <%
            for (InstJoinData data : joinList) {              
            %>
            <tbody id="question-list">
            <tr>
                <td><%=data.getMemberName()%></td>
                <td><%=data.getSportType()%></td>
               	<td><%=data.getBio() %></td>
               	<td><%=data.getExperienceYears() %></td>
               	<td><%=data.getCertifications() %></td>
               	<td><%=data.getLocation() %></td>
                <td>
                    <button type="submit" name="instructorID" value="<%=data.getInstructorID()%>">신청</button>
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