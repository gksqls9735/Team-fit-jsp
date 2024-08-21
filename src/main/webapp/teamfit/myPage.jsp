<%@page import="controller.InstMatchDAO"%>
<%@page import="model.UserVO"%>
<%@page import="controller.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<%
request.setCharacterEncoding("utf-8");
UserDAO dao = UserDAO.getInstance();
UserVO vo = dao.getMember(loginID);
InstMatchDAO matchDao = InstMatchDAO.getInstance();
String instName = matchDao.getInstName(loginID);
request.setAttribute("instName", instName);
%>
<main>
	<div class="signup">
		<h1>회원정보수정</h1>
		<div>
			<span><span class="re">●</span> 회원정보수정</span>
			<p>고객님의 정보는 개인정보보호정책에 의해 철저하게 보호됩니다.</p>
		</div>
		<form action="myPageProc.jsp" method="post">
			<table>
				<tr>
				<td class="td1"><label for="memberID">사용자ID <span
						class="re">★</span></label></td>
				<td class="td2"><%=vo.getMemberID()%></td>
			</tr>
				<tr>
				<td class="td1"><label for="memberPW">비밀번호 <span
						class="re">★</span></label></td>
				<td class="td2"><input onkeyup="pwCheck()" type="password"
					name="memberPW" id="memberPW" required size="20" maxlength="16"
					minlength="8" value="<%=vo.getMemberPW()%>" /> <span
					id="memberPWInfo"></span></td>
			</tr>
				<tr>
				<td class="td1"><label for="pwComfirm">비밀번호 확인 <span
						class="re">★</span></label></td>
				<td class="td2"><input onkeyup="pwComCheck()" type="password"
					name="pwComfirm" id="pwComfirm" required size="20" maxlength="20"
					value="<%=vo.getMemberPW()%>" /><br> <span id="pwComfirmInfo"></span></td>
			</tr>
				<tr>
				<td class="td1"><label for="memberName">성명 <span
						class="re">★</span></label></td>
				<td class="td2"><input onkeyup="nameCheck()" type="text"
					name="memberName" id="memberName" required size="20" maxlength="20"
					value="<%=vo.getMemberName()%>" /><br> <span
					id="memberNameInfo" style="color: rgb(190, 190, 190)"></span></td>
			</tr>
				<tr>
				<td class="td1"><label for="memberEmail">E-mail <span
						class="re">★</span></label></td>
				<td class="td2"><input onkeyup="emailCheck()" type="email"
					name="memberEmail" id="memberEmail" required size="30"
					maxlength="30" value="<%=vo.getMemberEmail()%>" /><br> <span
					style="color: rgb(190, 190, 190)">메일 수신여부<br>* 할인 이벤트
						정보 및 할인쿠폰, 관심분야 등 필요한 정보를 빠르게 받아보실 수 있습니다 <br>* 비밀번호 분실시
						E-mail로 확인해드리니, <span style="color: green;">정확한 E-mail주소를
							기입</span>해주세요.
				</span><br> <span id="memberEmailInfo"></span></td>
			</tr>
				<tr>
					<td class="td1"><label for="memberType">회원 유형 <span
							class="re">★</span></label></td>
					<td class="td2"><input type="radio" id="generalMember"
						name="memberType" value="general" required <%= "general".equals(vo.getMemberType()) ? "checked" : "" %>> 
						<label for="generalMember">일반 회원</label> 
						<input type="radio" <%= "instructor".equals(vo.getMemberType()) ? "checked" : "" %>
						id="instructorMember" name="memberType" value="instructor"
						required> <label for="instructorMember">강사</label></td>
				</tr>
				<tr>
				<td class="td1"><label for="postcode">주소 <span
						class="re">★</span></label></td>
				<td class="td2"><input type="text" name="postcode"
					id="postcode" required size="7" maxlength="5"
					value="<%=vo.getPostcode()%>" />
					<button type="button" onclick="postSearch()">우편번호 검색</button> <span
					id="postcodeInfo"></span><br> <input type="text"
					name="address" id="address" size="40" maxlength="50"
					value="<%=vo.getAddress()%>" /><br> <input type="text"
					name="detailAddress" id="detailAddress" size="40" maxlength="50"
					value="<%=vo.getDetailAddress()%>" /><br> <input
					type="text" name="extraAddress" id="extraAddress" size="40"
					maxlength="50" value="<%= vo.getExtraAddress()%>"/></td>
			</tr>
				<tr>
				<td class="td1"><label for="phoneNum">휴대번호 <span
						class="re">★</span></label></td>
				<td class="td2"><input onkeyup="phoneCheck()" type="tel"
					name="phoneNum" id="phoneNum" required size="20" maxlength="15"
					value="<%= vo.getPhoneNum()%>" /><br> <span id="phoneNumInfo"></span>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center; padding: 10px 0"><input
					type="submit" value="정보수정" onClick="updateCheck(event)" />&nbsp;&nbsp;
					<input type="button" value="회원 탈퇴"
					onClick="javascript:window.location='delete.jsp'" />&nbsp;&nbsp;
					<input type="button" value="취소"
					onClick="javascript:window.location='main.jsp'" /></td>
			</tr>
			<tr>
				<td class="td1">매칭된 강사 정보</td>
				<td class="td2"><%=instName != null ? instName : "없음" %></td>
			</tr>
			<c:if test="${not empty instName}">
			<tr>
				<td colspan="2" style="text-align: center; padding: 10px 0">
					<button type="button" name="loginID" value="<%=loginID %>" onclick="javascript:window.location='cancle_match.jsp';">매칭 취소</button>
				</td>
			</tr>
			</c:if>
			</table>
		</form>
	</div>
</main>
<%@ include file="bottom.jsp"%>