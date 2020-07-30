import java.util.ArrayList; 

public class Score {
	
	private String playerName;
	private int playerScore;
	private ArrayList<Word> alreadyFound; 
	
	//constructor, get/set score, get/set player name, calculates score for word
	//and updates overall score
	
	public Score(String name) {
		playerName = name; 
		playerScore = 0; 
		alreadyFound = new ArrayList<Word>();
	}
	
	public void setName(String name) {
		playerName = name; 
	}
	
	public String getName() {
		return playerName; 
	}
	
	public void setScore(int score) {
		playerScore += score; 
	}
	
	public int getScore() {
		return playerScore; 
	}
	
	public void scoreWord(Word word, Dictionary dict) throws InvalidWordException{
		boolean scorable = true; 
		word.validWord(dict); 
		if (alreadyFound.size() > 0) {
		for(Word used : alreadyFound) {
			if(used.toString().equals(word.toString())) {
				scorable = false; 
				throw new InvalidWordException("This word was already entered."); 
				}
			}
		}
		if (word.length() < 3) {
			scorable = false; 
			throw new InvalidWordException("Words must be at least 3 letters long."); 
		}
		if(scorable == true) {
			alreadyFound.add(word); 
			if(word.length() == 3 || (word.length() == 4)){
				setScore(1);
			}
			else if(word.length() == 5) {
				setScore(2);
			}
			else if(word.length() == 6) {
				setScore(3);
			}
			else if(word.length() == 7) {
				setScore(5);
			}
			else {
				setScore(11);
			}
			System.out.println("Current score: " + getScore()); 
			
		}
	}
	
}
