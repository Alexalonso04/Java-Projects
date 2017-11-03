import java.util.*;
import java.io.*;


public class GraduateStudent extends Student {
	private String major;
	private String advisor;


	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAdvisor() {
		return this.advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public void writeToFile() {
		try {
			PrintWriter print = new PrintWriter(super.getId() + "_graded.txt");
			print.println("Graduate Student " + super.getName()
					+"\nStudent ID: " + super.getId() 
					+"\nMajor: " + this.major
					+"\nAdvisor: " + this.advisor);

			ArrayList<String> errors = super.getErrorList();

			for (int i = 0; i < super.getErrorList().size() ; i++) {
				print.println("(" + (i+1) + ")" + errors.get(i));
			}

			print.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	GraduateStudent(String name, String id, String essay, ArrayList<String> errorList, String major, String advisor) {
		super(name,id,essay,errorList);
		this.major = major;
		this.advisor = advisor;
	}
}
