<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp"%>
<main>
<div class="form-container class-form">
	<h2>강사 정보 등록</h2>
	<form action="inst_regi_proc.jsp" method="POST">
	<input type="hidden" name="instructorID" value="<%=loginID%>">
        <label for="sportType">종목:</label><br>
        <select id="sportType" name="sportType" required>
            <option value="요가">요가</option>
            <option value="필라테스">필라테스</option>
            <option value="피트니스">피트니스</option>
            <option value="에어로빅">에어로빅</option>
            <option value="격투기">격투기</option>
            <option value="수영">수영</option>
            <option value="사이클링">사이클링</option>
            <option value="러닝">러닝</option>
            <option value="춤">춤</option>
            <option value="역도">역도</option>
        </select><br><br>

        <label for="bio">소개:</label><br>
        <textarea id="bio" name="bio" rows="4" cols="50" required></textarea><br><br>

        <label for="experienceYears">경력:</label><br>
        <input type="number" id="experienceYears" name="experienceYears" required><br><br>

        <label for="certifications">자격증:</label><br>
        <input type="text" id="certifications" name="certifications" required><br><br>

        <label for="location">지역:</label><br>
        <input type="text" id="location" name="location" required><br><br>

        <input type="submit" value="등록">

	</form>
</div>
</main>
<%@ include file="bottom.jsp"%>