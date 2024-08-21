package model;

public class JoinData {
	private CartVO cvo;
	private LectureVO lvo;
	private UserVO uvo;
	
	public JoinData() {
		super();
	}

	public JoinData(CartVO cvo, LectureVO lvo, UserVO uvo) {
		super();
		this.cvo = cvo;
		this.lvo = lvo;
		this.uvo = uvo;
	}

	public CartVO getCvo() {
		return cvo;
	}

	public void setCvo(CartVO cvo) {
		this.cvo = cvo;
	}

	public LectureVO getLvo() {
		return lvo;
	}

	public void setLvo(LectureVO lvo) {
		this.lvo = lvo;
	}

	public UserVO getUvo() {
		return uvo;
	}

	public void setUvo(UserVO uvo) {
		this.uvo = uvo;
	}

	@Override
	public String toString() {
		return "JoinData [cvo=" + cvo + ", lvo=" + lvo + ", uvo=" + uvo + "]";
	}
	
	
	
}
