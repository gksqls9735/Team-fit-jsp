<%@page import="model.UserVO"%>
<%@page import="controller.UserDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
UserDAO dao = UserDAO.getInstance();
List<UserVO> memberList = dao.getAllMembers();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<main>
<div class="support-list-container">
    <form action="memListProc.jsp" method="post">
        <table class="cs_list">
        <thead>
            <tr>
                <th>아이디</th>
                <th>비밀번호</th>
                <th>성명</th>
                <th>이메일</th>
                <th>강사여부</th>
                <th>우편번호</th>
                <th>주소</th>
                <th>상세주소</th>
                <th>추가주소</th>
                <th>전화번호</th>
                <th>가입일</th>
                <th>삭제</th>
            </tr>
        </thead>
            <%
            for (UserVO data : memberList) {
                String formattedRegDate = null;
                if (data.getRegDate() != null) {
                    formattedRegDate = sdf.format(data.getRegDate());
                }
            %>
            <tbody id="question-list">
            <tr>
                <td><%=data.getMemberID()%></td>
                <td><%=data.getMemberPW()%></td>
                <td><%=data.getMemberName()%></td>
                <td><%=data.getMemberEmail()%></td>
                <td><%=data.getMemberType()%></td>
                <td><%=data.getPostcode()%></td>
                <td><%=data.getAddress()%></td>
                <td><%=data.getDetailAddress()%></td>
                <td><%=data.getExtraAddress()%></td>
                <td><%=data.getPhoneNum()%></td>
                <td><%=formattedRegDate%></td> 
                <td><input type="checkbox" name="delete" value="<%=data.getMemberID()%>"></td>          			
            </tr>
            <%
            }
            %>
            </tbody>
        </table>
        <div style="text-align: center; margin-top: 20px;">
            <input type="submit" value="삭제">
            <input type="button" value="메인페이지"
							onclick="window.location='main.jsp'">
        </div>
    </form>
</div>
</main>
<%@ include file="bottom.jsp"%>