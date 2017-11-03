import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class UndergraduateStudent extends Student {
	private String major;

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void writeToFile() {
		try {
			PrintWriter print = new PrintWriter(super.getId() + "_graded.txt");
			print.println("Undergraduate Student " + super.getName()
					+"\nStudent ID: " + super.getId()
					+"\nMajor: " + this.major);

			ArrayList<String> errors = super.getErrorList();

			for (int i = 0; i < super.getErrorList().size() ; i++) {
				print.println("(" + (i+1) + ")" + errors.get(i));
			}
			print.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		}
	}

	UndergraduateStudent(String name, String id, String essay, ArrayList<String> errorList, String major) {
		super(name,id,essay,errorList);
		this.major = major;
	}
}
