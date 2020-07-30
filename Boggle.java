import java.io.FileNotFoundException; 
import java.awt.Point;
import java.util.Scanner; 
import java.util.InputMismatchException; 

public class Boggle {
	
	//variables holding static objects - stay with the class -> don't have to keep re-making them
	private static Board game;
	private static GameTimer timer;
	private static Score currentScore; 
	private static Dictionary dict; 
	private static Scoreboard scores; 
	
	//variable holding time length of the game
	private final static int MAX_GAME_TIME = 50; 
	
	//variables holding the locations of files
	private final static String DICT_FILENAME = "words.txt";
	private final static String HIGHSCORES_FILENAME = "BoggleHighScores.txt";

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Welcome to Boggle");
		Scanner in = new Scanner(System.in);
		gameSetup(in);
		game.printBoard(); 
		boolean playing = true; 
		while(playing) {
			while(!gameOver()) { 
				System.out.println("Please enter a letter coordinate");
				boolean wordMade = false; 
				Point letterCoordinate = new Point(0, 0); 
				Word aWord = new Word("");  
				String letter = "";
				while(!wordMade) {
					try {
						letterCoordinate = game.onBoard(in);
						if(gameOver()) {
							wordMade = true; 
						}
						letter = game.getLetter((int)letterCoordinate.getX() - 1, (int)letterCoordinate.getY() - 1); 
						aWord.addLetter(letter); 
						game.printBoard();
						if(game.wordFinished(letterCoordinate)) 
						{
							aWord.removeLetter(letter);  
							currentScore.scoreWord(aWord, dict); 
							letterCoordinate = new Point(0, 0); 
							aWord = new Word(""); 
							wordMade = true; 
						}
						aWord.printWord(); 
						System.out.println("Time remaining: " + timer.getTimeRemaining());
						}
					catch(InvalidLocationException e) {
						game.printBoard();
						System.out.println("Time remaining: " + timer.getTimeRemaining());
						System.out.println("Invalid Location: " + e.getMessage()); 
					}
					catch(InvalidWordException e) {
						wordMade = true; 
						game.printBoard(); 
						System.out.println("Time remaining: " + timer.getTimeRemaining());
						System.out.println("Invalid Word: " + e.getMessage());
					}
					catch(InputMismatchException e) {
						game.printBoard();
						System.out.println("Time remaining: " + timer.getTimeRemaining());
						System.out.println("Input Mismatch Exception: " + e.getMessage()); 
					}
				}
			}
			try {
				System.out.println("Final Score: " + currentScore.getScore());
				scores.sortScores(currentScore);
				}
				catch(InputMismatchException e) {
					game.printBoard();
					System.out.println("Time remaining: " + timer.getTimeRemaining());
					System.out.println("Input Mismatch Exception: " + e.getMessage()); 
				}
		System.out.println("Play again (y/n)?: ");
		String play = in.next();
		if(play.equalsIgnoreCase("y")) {
			gameSetup(in); 
			game.printBoard(); 
			playing = true; 
		}
		else if (play.equalsIgnoreCase("n")) {
			playing = false;
		}
		else {
			System.out.println("Invalid input!");
		}
		}
	}
	
	private static void gameSetup(Scanner in) {
		game = new Board(); 
		String playerName;
		System.out.print("Enter player name: ");
		playerName = in.next(); 
		in.nextLine();
		currentScore = new Score(playerName); 
	    scores = new Scoreboard(currentScore, HIGHSCORES_FILENAME);
	    try {
	    	dict = new Dictionary(DICT_FILENAME);
	    } 
	    catch(FileNotFoundException e) {
	    	System.out.println("The file for the dictionary was not found");
	    	System.exit(0);
	    }
	    
	    printGameObjective();
	    printMoveInstructions();
	    printMakingWordsInstructions();
	    
	    System.out.println("Press ENTER to begin");
	    in.nextLine();
	    timer = new GameTimer(MAX_GAME_TIME); 
	    		
		
	}
	
	private static boolean gameOver() {
		if (timer.getTimeRemaining() == 0) {
			System.out.println("Time's up!");
			return true;
		}
		return false; 
	}
	
	/**
	 * Prints the instructions of the object of the game of Boggle.
	 */
	private static void printGameObjective() {
		System.out.println("\n\nBoggle is designed to make as many words as possible in 180 seconds.\n");
	}

	/**
	 * Prints the instructions for where a player can move between letters.
	 */
	private static void printMoveInstructions() {
		System.out.println("Combine letters above/below, right/left, or diagonally from the previous letter.\n"
				+ "You cannot go back over a cube that has been used.\n");
	}

	/**
	 * Prints the instructions finding words.
	 */
	private static void printMakingWordsInstructions() {
		System.out.println("Enter coordinates of letter cubes followed by ENTER to make a word.\n"
				+ "Submit the same coordinates twice to finish the word to be scored.\n"
				+ "Words must be three or more letters long and found in the English language Dictonary.\n");
	}
}
