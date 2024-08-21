package model;

public class InstMatchVO {
	private int no;
	private String memberID;
	private String instructorID;
	
	public InstMatchVO() {
		super();
	}

	public InstMatchVO(int no, String memberID, String instructorID) {
		super();
		this.no = no;
		this.memberID = memberID;
		this.instructorID = instructorID;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	@Override
	public String toString() {
		return "InstMatchVO [no=" + no + ", memberID=" + memberID + ", instructorID=" + instructorID + "]";
	}

	
	
	
}
