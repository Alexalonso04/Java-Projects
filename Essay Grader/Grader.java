import java.io.*;
import java.util.*;

public class Grader {
	private boolean available;
	private Student student;
	private Dictionary dict;

	public Grader (Dictionary dic) {
		this.dict = dic;
		available = true;
	}

	public boolean gradeStudent (String fileName) {
		Scanner scan = null;
		boolean sucess = true;
		File studentEssay = new File(fileName + ".txt");
		//File graded = new File (fileName + "_graded.txt");

		//Elements to look for
		ArrayList<String> errors = new ArrayList<String>();
		String essay = "";
		String studentType = null;
		String name = null;
		String id = null;
		String major = null;
		String advisor = null;
		String school = null;
		String word = null;


		try {
			scan = new Scanner(studentEssay);
			//PrintWriter print = new PrintWriter (fileName + "_graded.txt");

			//		  studentType = scan.next() + " " + scan.next();
			studentType = scan.nextLine();

			// boolean simplified = true; //Used to determine whether a word needs to be simplified or not

			//Checks for Student Type to determine what information to look for
			switch (studentType) {
			case "Graduate Student":
				name = scan.nextLine();
				id = scan.nextLine();
				major = scan.nextLine();
				advisor = scan.nextLine();


				while (scan.hasNext()) {
					word = scan.next();

					//Fills up the 'essay' string
					essay = essay + word + " " ;

					//Eliminates unwanted characters from each word
					if (word.contains(".")){
						word = word.substring(0, word.indexOf("."));
					} else if (word.contains(",")) {
						word = word.substring(0, word.indexOf(","));
					} else if (word.contains(":")) {
						word = word.substring(0, word.indexOf(":"));
					} else if(word.contains("?")) {
						word = word.substring(0, word.indexOf("?"));
					} else if (word.contains("/")) {
						word = word.substring(0, word.indexOf("/"));
					}
					if (!dict.isWord(word)) {
						errors.add(word);
					}
				}
				//Initializes a new Student
				student = new GraduateStudent(name,id,essay,errors,major,advisor);
				available = true;
				sucess = true;
				
				//Testing variables
//				System.out.println("I got that the student name was: " + name + "\nAnd that"
//						+ " the id was: " + id + "\nAlso the major is: " + major + "\nand the "
//						+ "advisor is: " + advisor + 
//						"\nThe Student type is: " +  studentType + "\n" + 
//						"Also here is your essay: " + "\n" + essay);

				//Testing for printing
//								print.println("Student Type: " + studentType + "\nName: " + name + "\nID: " + id + "\nMajor: " + major + "\nAdvisor: " + advisor);
//				
//								for (int i = 0; i < errors.size(); i++) {
//									print.println(errors.get(i));
//								}

				break;
			case "Undergraduate Student":
				name = scan.nextLine();
				id = scan.nextLine();
				major = scan.nextLine();

				while (scan.hasNext()) {
					word = scan.next();
					int count = 0;
					//Fills up the 'essay' string
					essay = essay + word + " ";
					//Eliminates unwanted characters from each word of the essay. "." and ",".
					if (word.contains(".")){
						word = word.substring(0, word.indexOf("."));
					} else if (word.contains(",")) {
						word = word.substring(0, word.indexOf(","));
					} else if (word.contains(":")) {
						word = word.substring(0, word.indexOf(":"));
					} else if(word.contains("?")) {
						word = word.substring(0, word.indexOf("?"));
					}
					if (!dict.isWord(word)) {
						errors.add(word);
					}
				}

				//Initializes a student of type Undergraduate
				student = new UndergraduateStudent(name,id,essay,errors,major);
				available = true;
				sucess = true;

//				System.out.println("I got that the student name was: " + name + "\nAnd that"
//						+ " the id was: " + id + "\nAlso the major is " + major + 
//						"\nThe Student type is: " +  studentType + "\nAlso here is your essay"
//						+" as a bonus:" +"\n" + essay +
//						"\nOh, and you also made these mistakes " + errors);
				break;

			case "HighSchool Student":
				name = scan.nextLine();
				id = scan.nextLine();
				school = scan.nextLine();

				while (scan.hasNext()) {
					word = scan.next();
					//Fills up the 'essay' string
					essay = essay + word + " ";


					//Eliminates unwanted characters from each word of the essay. "." and ",".
					if (word.contains(".")){
						word = word.substring(0, word.indexOf("."));
					} else if (word.contains(",")) {
						word = word.substring(0, word.indexOf(","));
					} else if (word.contains(":")) {
						word = word.substring(0, word.indexOf(":"));
					} else if(word.contains("?")) {
						word = word.substring(0, word.indexOf("?"));
					}
					if (!dict.isWord(word)) {
						errors.add(word);
					}
				}
				student = new HighSchoolStudent(name,id,essay,errors,school);
				available = true;
				sucess = true;

//				System.out.println("I got that the student name was: " + name + "\nAnd that"
//						+ " the id was: " + id + "\nFrom: " + school +"\n" + "This is your essay " + essay +
//						"\nOh, and you also made these mistakes " + errors);
				break;
			}
		}catch(FileNotFoundException e) {
			sucess = false;
			reset();
		}
		return sucess;
	}

	public boolean isAvailable () {
		return (available && student == null) ? true : false;
	}

	public Student getStudent() {
		return this.student;
	}

	public void reset() {
		student = null;
		available = true;
	}
}
