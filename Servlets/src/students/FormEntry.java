package students;

import java.io.*;
import java.util.*;

public class FormEntry {
	public static final String STUDENT_FILE = "student.txt";
	
	private Map<String, StudentInfo> sInfoMap = new HashMap<String, StudentInfo>();

	public FormEntry() throws IOException {
		this(STUDENT_FILE); 
	}
	public FormEntry(String fname) throws IOException {
		this(new BufferedReader(new FileReader(fname)));
	}
	
	private FormEntry(BufferedReader br) throws IOException {	
		String fName = null;
		String lName = null;
		String id = null;
		String languagesKnown = null;
		String daysAvailable = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				fName  = nextLine;
				lName = br.readLine();
				languagesKnown = br.readLine();
				daysAvailable = br.readLine();
				id = br.readLine();
				addInfo(fName, lName, languagesKnown, daysAvailable, id);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error process phonebook");
			throw new IOException("Could not process phonebook file");
		}
	}

	public void savePhoneBook(String fname)
	{
		try {
			System.out.println("Opening " + fname);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fname));
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++)
				pw.println(entries[i]);

			pw.close();
		}
		catch (Exception exc)
		{ 
			exc.printStackTrace(); 
			System.out.println("Error saving phone book");
		}
	}
	

	public void addInfo(String fname, String lname, String lang, String days, String id)
	{ 
		addInfo(id, new StudentInfo(fname, lname, lang, days, id));
	}

	public void addInfo(String number, StudentInfo newEntry)
	{ sInfoMap.put(number, newEntry); }

	public String[] listEntries()
	{
		String[] rval = new String[sInfoMap.size()];
		int i = 0;
		StudentInfo nextEntry = null;
		for (Iterator<StudentInfo> iter = sInfoMap.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
}