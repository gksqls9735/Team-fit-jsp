<%@page import="model.JoinData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
    CartDAO dao = CartDAO.getInstance();
    ArrayList<JoinData> list = dao.getMyList(loginID);
    request.setAttribute("list", list); // 리스트를 요청 속성으로 설정합니다.
    for(JoinData data : list){
        System.out.println(data.toString());
    }
%>
<main>
<div class="myList">
    <c:if test="${empty list}">
        <script type="text/javascript">
            alert("수강신청목록이 없습니다.");
            history.go(-1);
        </script>
    </c:if>
    <form action="cancel_lecture.jsp" method="post">
    <c:if test="${not empty list}">
        <c:forEach var="item" items="${list}">
        <table class="my_list">
            <thead>
                <tr>
                    <td colspan="2">나의 강의 목록</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>강의 일련번호</td>
                    <td>${item.cvo.getNo()}</td>
                </tr>
                <tr>
                    <td>강의 코드</td>
                    <td>${item.cvo.getCode()}</td>
                </tr>
                <tr>
                    <td>강의 제목</td>
                    <td>${item.lvo.getTitle()}</td>
                </tr>
                <tr>
                    <td>강의 설명</td>
                    <td>${item.lvo.getL_description()}</td>
                </tr>
                <tr>
                    <td>강의 종목</td>
                    <td>${item.lvo.getL_type()}</td>
                </tr>
                <tr>
                    <td>강의 날짜</td>
                    <td>${item.lvo.getDate_time()}</td>
                </tr>
                <tr>
                    <td>강의 장소</td>
                    <td>${item.lvo.getL_location()}</td>
                </tr>
                <tr>
                    <td>강의료</td>
                    <td>${item.lvo.getFee()}</td>
                </tr>
                <tr>
                    <td>강의 준비물</td>
                    <td>${item.lvo.getRequirements()}</td>
                </tr>
                <tr>
                    <td>강사 이름</td>
                    <td>${item.uvo.getMemberName()}</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2"><button type="submit" name="no" value="${item.cvo.getNo()}">신청 취소</button></td>
                </tr>
            </tfoot>
        </table>
        </c:forEach>
    </c:if>
    </form>
</div>
</main>
<%@ include file="bottom.jsp"%>
