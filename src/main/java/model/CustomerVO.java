package model;

import java.sql.Timestamp;

public class CustomerVO {
	private int num;
    private int ref;
    private int step;
    private int depth;
    private int readcount;
    private String memberID;
    private String type;
    private String title;
    private String message;
    private Timestamp createdDate;
    
	public CustomerVO() {
		super();
	}

	public CustomerVO(int num, int ref, int step, int depth, int readcount, String memberID, String type, String title,
			String message, Timestamp createdDate) {
		super();
		this.num = num;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.readcount = readcount;
		this.memberID = memberID;
		this.type = type;
		this.title = title;
		this.message = message;
		this.createdDate = createdDate;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "CustomerVO [num=" + num + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", readcount="
				+ readcount + ", memberID=" + memberID + ", type=" + type + ", title=" + title + ", message=" + message
				+ ", createdDate=" + createdDate + "]";
	}
    
}
