package students;


public class StudentInfo {
	private String firstName;
	private String lastName;
	private String[] languagesKnown;
	private String[] daysAvailable;
	private String studentId;
	private String school;
	
	public StudentInfo(String firstName, String lastName,
			String[] languagesKnown, String[] daysAvailable, String id, String school) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.languagesKnown = languagesKnown;
		this.daysAvailable = daysAvailable;
		this.studentId = id;
		this.school = school;
	}

	public String toString() {
		return "Student:"+ this.firstName + " " + this.lastName+
				",knows:" + this.getLanguagesKnown()+",is from:"+this.getSchool()
				+ " and is free on:" + this.getDaysAvailable();
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLanguagesKnown() {
		String languages = "";
		for (String s : languagesKnown) {
			if(s.equals("None of the above")){
				s = "None of the languages";
			}
			languages = languages.concat(s.concat(","));
		}
		return languages.substring(0,languages.length()-1);
	}
	
	public String getDaysAvailable() {
		String days = "";
		for (String s : daysAvailable) {
			if(s.equals("None of the above")){
				s = "None of the days";
			}
			days = days.concat(s.concat(","));
		}
		return days.substring(0,days.length()-1);
	}
	
	public String getStudentId() {
		return studentId;
	}
	public String getSchool(){
		return school;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLanguagesKnown(String[] languagesKnown) {
		this.languagesKnown = languagesKnown;
	}

	public void setDaysAvailable(String[] daysAvailable) {
		this.daysAvailable = daysAvailable;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public void setSchool(String school) {
		this.school = school;
	}
}

