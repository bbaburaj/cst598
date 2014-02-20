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
	private static String searchSchool = "school";

	private Map<String, StudentInfo> sInfoMap = new Hashtable<String, StudentInfo>();

	public FormEntry() throws IOException {
		this(STUDENT_FILE);
	}

	public FormEntry(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}

	public FormEntry(InputStream st) throws IOException {
		this(new BufferedReader(new InputStreamReader(st)));
	}

	private FormEntry(BufferedReader br) throws IOException {
		String fName = null;
		String lName = null;
		String id = null;
		String[] languagesKnown = null;
		String[] daysAvailable = null;
		String school = null;

		try {
			String nextLine = null;
			while ((nextLine = br.readLine()) != null) {
				id = nextLine;
				fName = br.readLine();
				lName = br.readLine();
				languagesKnown = br.readLine().split(",");
				daysAvailable = br.readLine().split(",");
				school = br.readLine();
				addInfo(fName, lName, languagesKnown, daysAvailable, id, school);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error processing student entries");
			throw new IOException("Failed to process student.txt");
		}
	}

	public void addInfo(String fname, String lname, String[] lang,
			String[] days, String id, String school) {
		addInfo(id, new StudentInfo(fname, lname, lang, days, id, school));
	}

	public void addInfo(String number, StudentInfo newEntry) {
		sInfoMap.put(number, newEntry);
	}

	public void updateStudentFile(String name) throws IOException,
			URISyntaxException {
		File f = new File(name);
		BufferedWriter writer = new BufferedWriter(new FileWriter(
				f.getAbsoluteFile()));
		Iterator<StudentInfo> i = sInfoMap.values().iterator();
		while (i.hasNext()) {
			StudentInfo student = i.next();
			writer.write(student.getStudentId() + NEW_LINE);
			writer.write(student.getFirstName() + NEW_LINE);
			writer.append(student.getLastName() + NEW_LINE);
			writer.append(student.getLanguagesKnown() + NEW_LINE);
			writer.append(student.getDaysAvailable() + NEW_LINE);
			writer.append(student.getSchool() + NEW_LINE);
		}
		writer.close();
	}

	public int getTotalStudents() {
		return sInfoMap.size();
	}

	public List<String> listAllInformation() {
		List<String> output = new ArrayList<String>();
		Iterator<StudentInfo> it = sInfoMap.values().iterator();
		while (it.hasNext()) {
			StudentInfo st = it.next();
			output.add(st.toString());
		}
		return output;
	}

	public StudentInfo studentRegistered(String id) {
		return sInfoMap.get(id);
	}

	public List<String> searchEntry(String qString) {
		List<String> result = new ArrayList<String>();
		Iterator<StudentInfo> it = sInfoMap.values().iterator();
		StringTokenizer tokenizer = null;
		while (it.hasNext()) {
			StudentInfo st = it.next();
			tokenizer = new StringTokenizer(qString, "&");
			String output = "";
			while (tokenizer.hasMoreTokens()) {
				String[] searchFor = tokenizer.nextToken().split("=");
				if (searchFor[0].equalsIgnoreCase(searchFirstName)) {
					if (st.getFirstName().toLowerCase()
							.contains(searchFor[1].toLowerCase()))
						output = st.toString();
					else {
						break;
					}
				} else if (searchFor[0].equalsIgnoreCase(searchLastName)) {
					if (st.getLastName().toLowerCase()
							.contains(searchFor[1].toLowerCase()))
						output = st.toString();
					else {
						break;
					}
				} else if (searchFor[0].equalsIgnoreCase(searchSchool)) {
					searchFor[1] = searchFor[1].replaceAll("%20", " ");
					if (st.getSchool().toLowerCase()
							.contains(searchFor[1].toLowerCase()))
						output = st.toString();
					else {
						break;
					}
				} else if (searchFor[0].equalsIgnoreCase(searchLanguages)) {
					String lang[] = searchFor[1].toLowerCase().split("\\+");
					output = "";
					for (String s : lang) {
						s = s.replaceAll("cpp", "c++");
						s = s.replaceAll("jsharp", "j#");
						s = s.replaceAll("csharp", "c#");
						if (st.getLanguagesKnown().toLowerCase().contains(s)) {
							output = st.toString();
						}
					}
					if (output.length() == 0)
						break;

				} else if (searchFor[0].equalsIgnoreCase(searchAvailability)) {
					String days[] = searchFor[1].toLowerCase().split("\\+");
					output = "";
					for (String s : days) {
						if (st.getDaysAvailable().toLowerCase()
								.contains(s.toLowerCase())) {
							output = st.toString();
						}
					}
					if (output.length() == 0)
						break;
				} else {
					output = "Invalid Param";
					break;
				}
			}
			if (output.length() > 0)
				result.add(output);
		}
		return result;

	}

	public List<String> displayTopMatches(StudentInfo currentStudent) {
		HashMap<Integer, ArrayList<String>> priorityMap = new HashMap<Integer, ArrayList<String>>();
		List<String> output = new ArrayList<String>();
		String school = currentStudent.getSchool();
		String langStr = currentStudent.getLanguagesKnown();
		String daysStr = currentStudent.getDaysAvailable();
		String id = currentStudent.getStudentId();
		Iterator<StudentInfo> it = sInfoMap.values().iterator();
		ArrayList<String> existingList = new ArrayList<String>();
		while (it.hasNext()) {
			StudentInfo st = it.next();
			String lng = st.getLanguagesKnown();
			String availability = st.getDaysAvailable();
			String sch = st.getSchool();
			String details = st.toString();
			if (st.getStudentId().equals(id))
				continue;
			if(output.size()==3)break;
			if (lng.equals(langStr)
					&& availability.equals(daysStr)
					&& sch.equals(school)) {
				existingList= priorityMap.get(1);
				if(existingList!=null){
					existingList.add(details);
				}else{
					priorityMap.put(1, new ArrayList<String>());
					priorityMap.get(1).add(details);
				}
			}else if(availability.equals(daysStr) && (lng.equals(langStr) || sch.equals(school))){
				existingList= priorityMap.get(2);
				if(existingList!=null){
					existingList.add(details);
				}else{
					priorityMap.put(2, new ArrayList<String>());
					priorityMap.get(2).add(details);
				}
			}else if(lng.equals(langStr)
					|| availability.equals(daysStr)
					|| sch.equals(school)){
				existingList= priorityMap.get(3);
				if(existingList!=null){
					existingList.add(details);
				}else{
					priorityMap.put(3, new ArrayList<String>());
					priorityMap.get(3).add(details);
				}
			}

		}
		if(priorityMap.get(1)!=null){output.addAll(priorityMap.get(1));}
		if(priorityMap.get(2)!=null){output.addAll(priorityMap.get(2));}
		if(priorityMap.get(3)!=null){output.addAll(priorityMap.get(3));}
		return output;
	}

}