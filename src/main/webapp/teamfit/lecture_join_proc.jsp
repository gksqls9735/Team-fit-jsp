<%@page import="controller.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="vo" scope="page" class="model.CartVO">
    <jsp:setProperty name="vo" property="*" />
</jsp:useBean>
<%
System.out.println(vo.toString());
CartDAO dao = CartDAO.getInstance();
int flag = dao.checkCart(vo);
if (flag <= 0) {
    int flag2 = dao.insertCart(vo);
    if (flag2 == 1) {
    		String msg = "강의 신청 완료";
%>
				<script>
            alert('<%= msg %>');
        </script>
<%  
        response.sendRedirect("main.jsp");
    } else {
        String msg = "입력 실패";
%>
        <script>
            alert('<%= msg %>');
            history.go(-1);
        </script>
<%
    }
} else if (flag > 0) {
    String msg = "이미 수강신청을 완료한 과목입니다.";
    System.out.println(msg);
%>
    <script>
        alert('<%= msg %>');
        history.go(-1);
    </script>
<%
} else {
    String msg = "데이터베이스 오류입니다.";
    System.out.println(msg);
%>
    <script>
        alert('<%= msg %>');
        history.go(-1);
    </script>
<%
}
%>
