import java.util.*;
import java.io.*;

public class HighSchoolStudent extends Student {
	private String nameOfSchool;

	public String getSchoolName() {
		return this.nameOfSchool;
	}

	public void setSchoolName(String schoolName) {
		this.nameOfSchool = schoolName;
	}

	public void writeToFile() {
		try {
			PrintWriter print = new PrintWriter(super.getId() + "_graded.txt");
			print.println("High School Student " + super.getName()
					+"\nStudent ID: " + super.getId()
					+"\nSchool Name: " + this.nameOfSchool);

			ArrayList<String> errors = super.getErrorList();

			for (int i = 0; i < super.getErrorList().size() ; i++) {
				print.println("(" + (i+1) + ")" + errors.get(i));
			}
			print.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	public HighSchoolStudent(String name, String id, String essay, ArrayList<String> errorList, String school){
		super(name,id,essay,errorList);
		nameOfSchool = school;
	}
}
