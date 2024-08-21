<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>
<div class="form-container class-form">
	<h2>강의 생성</h2>
	<form action="c_lecture_proc.jsp" method="POST">
	<input type="hidden" name="instructorId" value="<%=loginID%>">
		<div class="form-group">
			<label for="title">강의 제목</label> <input type="text" id="title"
				name="title" required>
		</div>
		<div class="form-group">
			<label for="l_description">강의 설명</label>
			<textarea id="l_description" name="l_description" rows="4" required></textarea>
		</div>
		<div class="form-group">
			<label for="l_type">강의 유형</label> <select id="l_type" name="l_type"
				required>
				<option value="요가">요가</option>
				<option value="필라테스">필라테스</option>
				<option value="hiit">HIIT</option>
			</select>
		</div>
		<div class="form-group">
			<label for="date_time">강의 날짜 및 시간</label> <input
				type="datetime-local" id="date_time" name="date_time" required>
		</div>
		<div class="form-group">
			<label for="l_location">강의 장소</label> <input type="text" id="l_location"
				name="l_location" required>
		</div>
		<div class="form-group">
			<label for="l_capacity">참가자 제한 인원</label> <input type="number"
				id="l_capacity" name="l_capacity" required>
		</div>
		<div class="form-group">
			<label for="level">강의 난이도</label> <select id="level" name="l_level"
				required>
				<option value="초급">초급</option>
				<option value="중급">중급</option>
				<option value="고급">고급</option>
			</select>
		</div>
		<div class="form-group">
			<label for="requirements">준비물</label> <input type="text"
				id="requirements" name="requirements">
		</div>
		<div class="form-group">
			<label for="fee">참가비용</label> <input type="number" id="fee"
				name="fee" required>
		</div>
		<input type="submit" value="강의 생성"/>
	</form>
</div>
</main>
<%@ include file="bottom.jsp"%>