<%@page import="java.text.SimpleDateFormat"%>
<%@page import="controller.CustomerDAO"%>
<%@page import="model.CustomerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
// 수정 <1>
//멤버변수 한 번에 보여줄 수 있는 pagerow
int pageSize = 10;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//<수정 2> 현재 페이지 지정이 없으면 기본으로 1page로 설정
String pageNum = request.getParameter("pageNum");
if (pageNum == null) {
	pageNum = "1";
}
int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage - 1) * pageSize + 1;
int endRow = currentPage * pageSize;
System.out.println(startRow);
System.out.println(endRow);
System.out.println(currentPage);
int count = 0;
int number = 0;
List<CustomerVO> list = null;
CustomerDAO dao = CustomerDAO.getInstance();
count = dao.getArticleCount();//전체 글수
if (count > 0) {
	// 진짜 글을 가져와서 저장
	list = dao.getArticles(startRow, endRow);//수정<3>
} else {
%>	
	<script type="text/javascript">
	alert("게시판에 글이 존재하지 않습니다.");
	</script>
	<meta http-equiv="Refresh" content="1;main.jsp">
<%
}
%>
<% 
//번호순으로 보여주고 싶을 때 계산
number = count - (currentPage - 1) * pageSize; //수정<4>
for(CustomerVO data : list){
	System.out.println(data.toString());
}
%>
<main>
    <div class="support-list-container">
        <h2>자유게시판 목록</h2>
        <div class="filter-group">
            <label for="filter">문의 유형</label> 
            <select id="filter" name="filter" onchange="filterQuestions()">
                <option value="all">전체</option>
								<option value="일반">일반 문의</option>
								<option value="강의">강의 관련 문의</option>
								<option value="강사">강사 관련 문의</option>
								<option value="결제">결제 문의</option>
								<option value="기타">기타</option>
            </select>
        </div>
        <div class="table-header">
        	<input type="button" value="글작성" class="list-btn" onclick="document.location.href='customer_support.jsp'">
      	</div>
        <table class="cs_list">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>문의 유형</th>
                    <th>조회수</th>
                    <th>등록 날짜</th>
                </tr>
            </thead>
            <%
            for (int i = 0; i < list.size(); i++){
            	CustomerVO vo = list.get(i);
            %>
            <tbody id="question-list">
              <tr>
                 <td><%= number-- %></td>
                 <td>
                 <%
                 int wid = 0;
                 if(vo.getDepth() > 0){
                	 wid = 5 * (vo.getDepth());
                 %>
                 	<img src="images/level.gif" width="<%=wid%>" height="16">
                 	<img src="images/re.gif">
                 <%}else{%>
                 <img src="images/level.gif" width="<%=wid%>" height="16">
                 <% }// if end %>
                 <a href="csContent.jsp?num=<%=vo.getNum()%>&amp;pageNum=<%=currentPage%>"><%=vo.getTitle()%></a>
                 <% if (vo.getReadcount() >= 20){%>
                  <img src="images/hot.gif" border="0"  height="16"><%}%></td>
                 <td><%=vo.getType()%></td>
                 <td><%=vo.getReadcount()%></td>
                 <td><%=sdf.format(vo.getCreatedDate()) %></td>
              </tr>
            </tbody>
            <%} // for end%> 
        </table>
	        	<div class="pagination">
            <%if(count > 0) {
                int pageBlock = 5;
                int imsi = count % pageSize == 0 ? 0 : 1;
                int pageCount = count / pageSize + imsi;
                int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
                int endPage = startPage + pageBlock - 1;
                if (endPage > pageCount) endPage = pageCount;
                if (startPage > pageBlock){%>
                <a href="customer_support_list.jsp?pageNum=<%=startPage - pageBlock%>">&laquo;</a>
            <%
                }//if end
                for(int i = startPage; i <= endPage; i++){ %>
									<a href="customer_support_list.jsp?pageNum=<%=i%>" class="${i eq currentPage ? 'active' : ''}"><%=i%></a>
            <%  	
                }//for end
                if(endPage < pageCount){%>
                <a href="customer_support_list.jsp?pageNum=<%=startPage + pageBlock%>">&raquo;</a>
           <%}// if end
            }// if end%>
	        </div>
    </div>
</main>
<%@ include file="bottom.jsp"%>