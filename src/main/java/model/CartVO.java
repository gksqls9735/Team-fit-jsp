package model;

public class CartVO {
	private int no;
	private int code;
	private String memberID;
	
	public CartVO() {
		super();
	}

	public CartVO(int no, int code, String memberID) {
		super();
		this.no = no;
		this.code = code;
		this.memberID = memberID;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	@Override
	public String toString() {
		return "Cart [no=" + no + ", code=" + code + ", memberID=" + memberID + "]";
	}
	
	
}
