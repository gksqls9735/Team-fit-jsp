<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>
	<div class="signup pwsearch">
		<h1>비밀번호 찾기</h1>
		<div>
			<span><span class="re">●</span> 온라인 회원가입작성</span>
			<p>고객님의 정보는 개인정보보호정책에 의해 철저하게 보호됩니다.</p>
		</div>
		<form action="myPwSearchProc.jsp" method="post">
			<table>
				<tr>
					<td class="td1"><label for="memberID">사용자ID <span
							class="re">★</span></label></td>
					<td class="td2"><input onkeyup="idCheck()" type="text"
						name="memberID" id="memberID" required size="20" maxlength="20" />
						<br>
					<span id="memberIDInfo"></span></td>
				</tr>
				<tr>
					<td class="td1"><label for="memberName">성명 <span
							class="re">★</span></label></td>
					<td class="td2"><input onkeyup="nameCheck()" type="text"
						name="memberName" id="memberName" required size="20"
						maxlength="20" /><br> <span id="memberNameInfo"
						style="color: rgb(190, 190, 190)"></span></td>
				</tr>
				<tr>
					<td class="td1"><label for="phoneNum">전화번호 <span
							class="re">★</span></label></td>
					<td class="td2"><input onkeyup="phoneCheck()" type="tel"
						name="phoneNum" id="phoneNum" required size="20" maxlength="15"
						placeholder="010-1111-1111" /><br> <span id="phoneNumInfo"></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center; padding: 10px 0"><input
						type="submit" name="sub" onclick="pwSearchCheck(event)" value="확인">
					</td>
				</tr>
			</table>
		</form>
	</div>
</main>
<%@ include file="bottom.jsp"%>