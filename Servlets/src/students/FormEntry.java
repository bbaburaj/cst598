package students;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class FormEntry {
	public static final String STUDENT_FILE = "student.txt";
	public static final String NEW_LINE = System.getProperty("line.separator");
	private static String searchFirstName = "firstname";
	private static String searchLastName = "lastname";
	private static String searchLanguages = "languages";
	private static String searchAvailability = "days";
	
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
	{ sInfoMap.put(number, newEntry);}
	
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
	
	public List<String> searchEntry(String qString){
		List<String> result = new ArrayList<String>();
		Iterator<StudentInfo> it =sInfoMap.values().iterator();
		StringTokenizer tokenizer =null;
		while(it.hasNext()){
			StudentInfo st = it.next();
			tokenizer = new StringTokenizer(qString,"&");
			String output = "";
			while(tokenizer.hasMoreTokens()){
				String[] searchFor = tokenizer.nextToken().split("=");
				System.out.println(searchFor[0]+" "+searchFor[1]);
				if(searchFor[0].equalsIgnoreCase(searchFirstName)){
					if(st.getFirstName().contains(searchFor[1]))output = st.toString();
					else{break;}
				} 
				if(searchFor[0].equalsIgnoreCase(searchLastName)){
					if(st.getLastName().contains(searchFor[1]))output = st.toString();
					else{break;}
				} 
				if(searchFor[0].equalsIgnoreCase(searchLanguages)){
					String lang[] = searchFor[1].split("\\+");
					output="";
					for(String s:lang){
						if(st.getLanguagesKnown().contains(s)){
							output = st.toString();
						}
					} 
					if(output.length()==0)break;
					
				}
				if(searchFor[0].equalsIgnoreCase(searchAvailability)){
					String days[] = searchFor[1].split("\\+");
					output="";
					for(String s:days){
						if(st.getDaysAvailable().contains(s)){
							output = st.toString();
						}
					}
					if(output.length()==0)break;
				}
			}
			System.out.println("["+output+"]");
			if (output.length()>0)result.add(output);
		}
		return result;
		
	}
	

}