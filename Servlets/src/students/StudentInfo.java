package students;

import java.util.List;


public class StudentInfo {
	private String firstName;
	private String lastName;
	private String languagesKnown;
	private String daysAvailable;
	private String studentId;
	
	public StudentInfo(String firstName, String lastName, String languagesKnown,
			String daysAvailable, String id){
		this.firstName = firstName;
		this.lastName = lastName;
		this.languagesKnown = languagesKnown;
		this.daysAvailable = daysAvailable;
		this.studentId = id;
	}

	public String toString(){
		return "Student: "+this.firstName+" "+this.lastName+" ID: "+this.studentId+"\n";
		
	}
}
