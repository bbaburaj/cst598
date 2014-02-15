package students;


public enum DaysOfWeek {

	MONDAY("Monday"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY("Sunday");
	private String day;
	DaysOfWeek(String day){
		this.day = day;
	}
	
	public String toString(){
		return this.day;	
	}
}
