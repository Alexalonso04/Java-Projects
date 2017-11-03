import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/* Testing Complete.
 * Working: 100%.
 */
public class Dictionary{
	private ArrayList<String> dictionary = new ArrayList<>();  

	public int getVocabularySize() {
		return dictionary.size();
	}

	public boolean loadDictionaryFromFile(String filePath) {
		boolean sucess = true;
		File dictionaryFile = new File(filePath);
		Scanner scan = null;

		//Fills Dictionary ArrayList
		try {
			scan = new Scanner (dictionaryFile);
			while(scan.hasNextLine()) {
				String word = scan.nextLine();
				dictionary.add(word.trim().toLowerCase());
			}
			scan.close();
		} catch(FileNotFoundException notFound) {
			sucess = false;
		}  
		return sucess;
	}

	public boolean isWord(String word) {
		return (dictionary.contains(word.toLowerCase())) ? true : false;
	}

	public void Print() {
		PrintWriter print = null;
		try {
			print = new PrintWriter("mydictionary.txt");
		} catch(Exception e) {
			
		}
		  String array [] = new String [dictionary.size()];
		  for (int i = 0; i < array.length; i++){
			  array[i] = dictionary.get(i).toString();
		  }
		  
		  for(String value: array) {
			  print.println(value);
		  }
	  }
	  
}
