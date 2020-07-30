//package Boggle;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class designed to hold and check the letters in a word.
 * @author Mr. Turner
 */
public class Word {

	private final String FILENAME = "words.txt";
	private Dictionary dict;
	private ArrayList<String> letters;
	private String combined;
	private int wordLength;

	/**
	 * Constructs a word object and a dictionary.
	 * @param word The letters of the array that will compose the word.
	 * @throws FileNotFoundException If the text file holding the words
	 * 	in the dictionary is not found.
	 */
	public Word (ArrayList<String> word) throws FileNotFoundException {
		this.dict = new Dictionary(this.FILENAME);
		this.letters = new ArrayList<String>();
		this.combined = "";

		for (String letter : word) {
			this.letters.add(letter);
			this.combined += letter;
		}
		this.wordLength = this.letters.size();
	}
	
	public Word (String letter) {
		  this.combined = "";
		  this.letters = new ArrayList<String>();
		  this.letters.add(letter);
		  this.combined += letter;
		  this.wordLength = this.combined.length();
	}
	
	public void printWord() {
		  System.out.println(this.combined);
	}
	
	public void addLetter(String letter) {
		this.letters.add(letter);
		this.combined += letter;
		this.wordLength++;
	}
	
	public void removeLetter(String letter) {
		  this.letters.remove(this.letters.size() - 1);
		  this.combined  = this.combined.substring(0, this.letters.size() - 1);
		  this.wordLength--;
	}
	
	/**
	 * Allows the user to use he length method similar to Strings.
	 * @return The length of the word.
	 */
	public int length() {
		return this.wordLength;
	}

	/**
	 * Determines whether or not the word is in the English dictionary.
	 * @return Whether the word was found
	 */
	public boolean validWord(Dictionary dict) throws InvalidWordException {
		for(String word: dict.getWords()) {
			if(word.equalsIgnoreCase(this.combined))
				return true;
		}
		throw new InvalidWordException("This word is not in the English dictionary.");
	}
	
	@Override
	public String toString(){
		return this.combined;
	}
}
