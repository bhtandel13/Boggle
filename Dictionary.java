import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class designed to keep track of all words in the English dictionary. - done
 * @author Mr. Turner
 */
public class Dictionary //can be used for multiple things - reusable code 
{

	/**
	 * Contains all of the words in the English Dictionary.
	 */
	private ArrayList<String> words = new ArrayList<String>();

	/**
	 * Creates a dictionary object.
	 * @param filename The text file holding all the words in the
	 * 	English dictionary with one word per line.
	 */
	public Dictionary(String filename) throws FileNotFoundException {
		//dont catch exception here b/c you cant do anything with it (run by another class)
		File text = new File(filename);
		Scanner wordReader = new Scanner(text);
		while(wordReader.hasNextLine()) {
			words.add(wordReader.nextLine());
		}
		wordReader.close();
	}

	/**
	 * Getter method for the words in the dictionary.
	 * @return The ArrayList of Strings containing all the words
	 */
	public ArrayList<String> getWords(){
		return words;
	}
	
	public boolean sort(Word word) {
		int range = 0; 
		for(int i = 0; i < words.size(); i ++) {
			String check = words.get(i); 
			if(word.toString().compareTo(check) > 0) {
				range = i + 1; 
			}
		}
		for (int i = 0; i <= range; i ++) {
			if(word.toString().equals(words.get(i))) {
				return true; 
			}
		}
		return false;
	}
}