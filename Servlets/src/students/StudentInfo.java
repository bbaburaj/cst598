package students;

import java.util.List;


public class StudentInfo {
	private String firstName;
	private String lastName;
	private List<String> languagesKnown;
	private List<DaysOfWeek> daysAvailable;
	private String type;
	
	public StudentInfo(String firstName, String lastName, List<String> languagesKnown,
			List<DaysOfWeek> daysAvailable, String type){
		this.firstName = firstName;
		this.lastName = lastName;
		this.languagesKnown = languagesKnown;
		this.daysAvailable = daysAvailable;
		this.type = type;
	}

	public String toString(){
		String output = "Student "+this.firstName+" "+this.lastName+" is a "+this.type+"\n";
		return output;
	}
}
