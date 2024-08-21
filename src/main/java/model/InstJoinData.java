package model;

public class InstJoinData {
	private String instructorID;
	private String sportType;
	private String bio;
	private int experienceYears;
	private String certifications;
	private String location;
	private String memberName;
	
	public InstJoinData() {
		super();
	}

	public InstJoinData(String instructorID, String sportType, String bio, int experienceYears, String certifications,
			String location, String memberName) {
		super();
		this.instructorID = instructorID;
		this.sportType = sportType;
		this.bio = bio;
		this.experienceYears = experienceYears;
		this.certifications = certifications;
		this.location = location;
		this.memberName = memberName;
	}

	public String getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(String instructorID) {
		this.instructorID = instructorID;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public int getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(int experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getCertifications() {
		return certifications;
	}

	public void setCertifications(String certifications) {
		this.certifications = certifications;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	
	
	
}
