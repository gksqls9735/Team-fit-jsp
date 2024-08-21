package model;

import java.util.Date;

public class UserVO {
	private String memberID;
	private String memberPW;
	private String memberName;
	private String memberEmail;
	private String memberType;
	private String postcode;
	private String address;
	private String detailAddress;
	private String extraAddress;
	private String phoneNum;
	private Date regDate;
	
	public UserVO() {
		super();
	}

	public UserVO(String memberID, String memberPW, String memberName, String memberEmail, String memberType,
			String postcode, String address, String detailAddress, String extraAddress, String phoneNum, Date regDate) {
		super();
		this.memberID = memberID;
		this.memberPW = memberPW;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberType = memberType;
		this.postcode = postcode;
		this.address = address;
		this.detailAddress = detailAddress;
		this.extraAddress = extraAddress;
		this.phoneNum = phoneNum;
		this.regDate = regDate;
	}

	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}

	public String getMemberPW() {
		return memberPW;
	}

	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getExtraAddress() {
		return extraAddress;
	}

	public void setExtraAddress(String extraAddress) {
		this.extraAddress = extraAddress;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "UserVO [memberID=" + memberID + ", memberPW=" + memberPW + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberType=" + memberType + ", postcode=" + postcode
				+ ", address=" + address + ", detailAddress=" + detailAddress + ", extraAddress=" + extraAddress
				+ ", phoneNum=" + phoneNum + "]";
	}

	
}
