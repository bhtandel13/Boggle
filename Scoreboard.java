import java.util.ArrayList;
import java.util.InputMismatchException; 
import java.util.Scanner; 
import java.io.PrintWriter; 
import java.io.File; 
import java.io.FileNotFoundException; 

public class Scoreboard {
	private ArrayList<Score> playerScores;
	private String fileName;
	
	public Scoreboard(ArrayList<Score> players, String fileName) {
		playerScores = new ArrayList<Score>(players);
		this.fileName = fileName; 
	}
	
	public Scoreboard(String player, String fileName) {
		playerScores = new ArrayList<Score>();
		playerScores.add(new Score(player));
		this.fileName = fileName; 
	}
	
	//used in boggle gameSetup()
	public Scoreboard(Score score, String fileName) {
		playerScores = new ArrayList<Score>();
		playerScores.add(score);
		this.fileName = fileName; 
	}
	
	public void setScore(String player, int score) {
		for(Score name: playerScores){
			if (name.getName().equals(player)) {
				name.setScore(score); 
			}
		}
	}
	
	public Score getScore(String player) {
		for(Score name: playerScores){
			if (name.getName().equals(player)) {
				return name; 
			}
		}
		return null; 
	}
	
	public void swap(int i, int j)
	{
		Score temp = playerScores.get(i);
		playerScores.set(i, playerScores.get(j));
		playerScores.set(j, temp);
	}
	
	private int maximumPosition(int from)
	{  
		int maxPos = from;
		for (int i = from + 1; i < playerScores.size(); i++)
		{
			if (playerScores.get(i).getScore() > playerScores.get(maxPos).getScore())
			{ 
				maxPos = i; 
			}
		}
		return maxPos;
	}
	
	
	public void sortScores(Score playerScore) throws InputMismatchException, FileNotFoundException{  
		File scores = new File(fileName);
		Scanner in = new Scanner(scores); 
		if (!in.hasNext()) {
			PrintWriter tester = new PrintWriter(scores); 
			tester.print("1 " + playerScore.getScore() + " " + playerScore.getName());
			tester.close(); 
		}
		else {
			while(in.hasNextLine()) {
				String line = in.nextLine(); 
				Scanner read = new Scanner(line);
				if(!read.hasNextInt()) {
					read.close(); 
					throw new InputMismatchException("Rank expected");
				}
				read.next(); 
				if(!read.hasNextInt()) {
					read.close(); 
					throw new InputMismatchException("Score expected");
				}
				int score = read.nextInt();
				if(!read.hasNext()) {
					read.close(); 
					throw new InputMismatchException("Name expected");
				}
				String name = read.next(); 
				Score add = new Score(name);
				add.setScore(score);
				playerScores.add(add); 
				read.close(); 
			}
			in.close();
			PrintWriter tester = new PrintWriter(scores); 
			for (int i = 0; i < playerScores.size() - 1; i++)
			{  
				int maxPos = maximumPosition(i);
				swap(maxPos, i);
			}
			for(int i = 1; i <= playerScores.size(); i ++) {
				if(i == playerScores.size()) {
					tester.print(i + " " + playerScores.get(i - 1).getScore() + " " + 
							playerScores.get(i - 1).getName());
				}
				else {
					tester.println(i + " " + playerScores.get(i - 1).getScore() + " " + 
							playerScores.get(i - 1).getName());
				}
			}
			tester.close(); 
		}
	}
}
