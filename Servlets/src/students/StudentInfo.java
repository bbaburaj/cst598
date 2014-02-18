package students;

import java.util.List;

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
		return "Student ID:"+this.studentId+", Name:"+ this.firstName + " " + this.lastName+
				" knows:" + this.getLanguagesKnown()+" is from:"+this.getSchool()
				+ "\r\nis free on:" + this.getDaysAvailable();
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
			languages = languages.concat(s.concat(","));
		}
		return languages.substring(0,languages.length()-1);
	}

	public String getDaysAvailable() {
		String days = "";
		for (String s : daysAvailable) {
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
}

