package students;

import java.util.List;


public class StudentInfo {
	private String firstName;
	private String lastName;
	private String[] languagesKnown;
	private String[] daysAvailable;
	private String studentId;
	
	public StudentInfo(String firstName, String lastName, String[] languagesKnown,
			String[] daysAvailable, String id){
		this.firstName = firstName;
		this.lastName = lastName;
		if(languagesKnown == null) {
			this.languagesKnown = new String[]{"No Info"};
		}else{
			this.languagesKnown = languagesKnown;
		}
		if(daysAvailable == null) {
			this.daysAvailable = new String[]{"No Info"};
		}else{
			this.daysAvailable = daysAvailable;
		}		
		this.studentId = id;
	}

	public String toString(){
		return "Student: "+this.firstName+" "+this.lastName+" ID: "+this.studentId+"\n";	
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLanguagesKnown() {
		String languages ="";
		for(String s:languagesKnown){
			languages =languages.concat(s.concat(","));
		}
		return languages;
	}

	public String getDaysAvailable() {
		String days ="";
		for(String s:daysAvailable){
			days =days.concat(s.concat(","));
		}
		return days;
	}

	public String getStudentId() {
		return studentId;
	}
}
