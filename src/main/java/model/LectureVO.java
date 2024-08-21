package model;

public class LectureVO {
	private int code; // 강의 코드
	private String instructorId;// 강사 아이디
	private String title; // 강의명
	private String l_description; // 강의 설명
	private String l_type; // 운동종류
	private String date_time; // 강의 날짜
	private String l_location; // 강의 장소
	private int applicants;
	private int l_capacity; // 최대 정원
	private String l_level; // 강의 난이도
	private String requirements;// 강의 준비물
	private int fee; // 비용

	public LectureVO() {
		super();
	}

	

	public LectureVO(int code, String instructorId, String title, String l_description, String l_type, String date_time,
			String l_location, int applicants, int l_capacity, String l_level, String requirements, int fee) {
		super();
		this.code = code;
		this.instructorId = instructorId;
		this.title = title;
		this.l_description = l_description;
		this.l_type = l_type;
		this.date_time = date_time;
		this.l_location = l_location;
		this.applicants = applicants;
		this.l_capacity = l_capacity;
		this.l_level = l_level;
		this.requirements = requirements;
		this.fee = fee;
	}



	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getL_description() {
		return l_description;
	}

	public void setL_description(String l_description) {
		this.l_description = l_description;
	}

	public String getL_type() {
		return l_type;
	}

	public void setL_type(String l_type) {
		this.l_type = l_type;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getL_location() {
		return l_location;
	}

	public void setL_location(String l_location) {
		this.l_location = l_location;
	}

	public int getApplicants() {
		return applicants;
	}

	public void setApplicants(int applicants) {
		this.applicants = applicants;
	}

	public int getL_capacity() {
		return l_capacity;
	}

	public void setL_capacity(int l_capacity) {
		this.l_capacity = l_capacity;
	}

	public String getL_level() {
		return l_level;
	}

	public void setL_level(String l_level) {
		this.l_level = l_level;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}



	@Override
	public String toString() {
		return "LectureVO [code=" + code + ", instructorId=" + instructorId + ", title=" + title + ", l_description="
				+ l_description + ", l_type=" + l_type + ", date_time=" + date_time + ", l_location=" + l_location
				+ ", applicants=" + applicants + ", l_capacity=" + l_capacity + ", l_level=" + l_level
				+ ", requirements=" + requirements + ", fee=" + fee + "]";
	}

	

}
