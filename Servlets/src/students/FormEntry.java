package students;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class FormEntry {
	public static final String STUDENT_FILE = "student.txt";
	public static final String NEW_LINE = System.getProperty("line.separator");
	
	private Map<String, StudentInfo> sInfoMap = new Hashtable<String, StudentInfo>();

	public FormEntry() throws IOException {
		this(STUDENT_FILE); 
	}
	public FormEntry(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	
	public FormEntry(InputStream st) throws IOException{
		this(new BufferedReader(new InputStreamReader(st)));
	}
	
	private FormEntry(BufferedReader br) throws IOException {	
		String fName = null;
		String lName = null;
		String id = null;
		String[] languagesKnown = null;
		String[] daysAvailable = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				id  = nextLine;
				fName = br.readLine();
				lName = br.readLine();
				languagesKnown = br.readLine().split(",");
				daysAvailable = br.readLine().split(",");
				addInfo(fName, lName, languagesKnown, daysAvailable, id);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error processing student entries");
			throw new IOException("Failed to process student.txt");
		}
	}

	public void addInfo(String fname, String lname, String[] lang, String[] days, String id)
	{ 
		addInfo(id, new StudentInfo(fname, lname, lang, days, id));
	}

	public void addInfo(String number, StudentInfo newEntry)
	{ sInfoMap.put(number, newEntry); 
	System.out.println(sInfoMap.size());}
	
	public void updateStudentFile(String name) throws IOException, URISyntaxException{
		File f = new File(name);
		BufferedWriter writer = new BufferedWriter(new FileWriter(f.getAbsoluteFile()));
		Iterator<StudentInfo> i = sInfoMap.values().iterator();
		while(i.hasNext()){
			StudentInfo student = i.next();
			writer.write(student.getStudentId()+NEW_LINE);
			writer.write(student.getFirstName()+NEW_LINE);
			writer.append(student.getLastName()+NEW_LINE);
			writer.append(student.getLanguagesKnown()+NEW_LINE);
			writer.append(student.getDaysAvailable()+NEW_LINE);
		}
		writer.close();
	}
	
	public int getTotalStudents(){
		return sInfoMap.size();
	}
	
	public List<String> listAllInformation(){
		List<String> output = new ArrayList<String>();
		Iterator<StudentInfo> it = sInfoMap.values().iterator();
		while(it.hasNext()){
			StudentInfo st = it.next();
			output.add(st.toString());
		}
		return output;
	}
	

}