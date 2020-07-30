import java.util.Arrays;
import java.util.ArrayList; 
import java.awt.Point;
import java.util.Random;
import java.util.Scanner; 

public class Board {
	private String[][] boggleBoard;
	private Boolean[] cubesUsed;
	private ArrayList<Point> used; 
	
	public Board() {
		boggleBoard = new String[4][4];
		Random generator = new Random();
		cubesUsed = new Boolean[16];
		used = new ArrayList<Point>(); 
		Arrays.fill(cubesUsed,  Boolean.FALSE);
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				while(boggleBoard[c][r] == null) {
					int cubeNum = generator.nextInt(16);
					if (cubesUsed[cubeNum] == false) {
						boggleBoard[c][r] = LetterCubes.rollLetterCube(cubeNum);
						cubesUsed[cubeNum] = true;
					}
				}
			}
		}
	}
	
	
	public String getLetter(int r, int c) { 
		return boggleBoard[r][c];
	}
	
	public void printBoard() {
		for(int r = 0; r < 4; r++) {
			for(int c = 0; c < 4; c++) {
				System.out.printf(" %2s ",  boggleBoard[r][c]);
			}
			System.out.println();
		}
		System.out.println(); 
	}
	
	public boolean wordFinished(Point entered) {
		if (used.size() == 0) {
			used.add(entered); 
			return false; 
		}
		else if (used.get(used.size() - 1).getX() == entered.getX() && used.get(used.size() - 1).getY() == entered.getY()) {
			used.clear(); 
			
			return true; 
		}
		used.add(entered);
		return false;
		
	} 
	
	public Point onBoard(Scanner in) throws InvalidLocationException {
		if(!in.hasNextInt()) {
			in.nextLine(); 
			throw new InvalidLocationException("Please enter numerical coordinates");
		}
		int x = in.nextInt(); 
		if(!in.hasNextInt()) {
			in.nextLine(); 
			throw new InvalidLocationException("Please enter numerical coordinates");
		}
		int y = in.nextInt();
		if (x < 1 || x > 4 || y < 1 || y > 4) {
			throw new InvalidLocationException("Please enter coordinates between 1 and 4");
		}
		Point letterCoordinate = new Point(x, y);
		if (used.size() > 0) {
			if(!nearbyPiece(letterCoordinate, used.get(used.size() - 1))
			&& !(used.get(used.size() - 1).getX() == letterCoordinate.getX() && used.get(used.size() - 1).getY() == letterCoordinate.getY())) {
				throw new InvalidLocationException("Choose a letter above/below, right/left, or diagonal from the preivous letter");
			}
			for(int i = 0; i < used.size() - 1; i++) {
				if (used.get(i).getX() == letterCoordinate.getX() && used.get(i).getY() == letterCoordinate.getY()) {
					throw new InvalidLocationException("This letter has already been used"); 
				}
			}
			
		}
		return letterCoordinate; 
	}
	
	public Boolean nearbyPiece(Point a, Point b) {
		if(a.getX() == b.getX() - 1) {
			if(a.getY() == b.getY() + 1) {
				return true;
			}
		}
		if(a.getX() == b.getX() + 1) {
			if(a.getY() == b.getY() + 1) {
				return true;
			}
		}
		if(a.getX() == b.getX()) {
			if(a.getY() == b.getY() + 1) {
				return true;
			}
		}
		if(a.getX() == b.getX()) {
			if(a.getY() == b.getY() - 1) {
				return true;
			}
		}
		if(a.getX() == b.getX() - 1) {
			if(a.getY() == b.getY() - 1) {
				return true;
			}
		}
		if(a.getX() == b.getX() + 1) {
			if(a.getY() == b.getY()) {
				return true;
			}
		}
		if(a.getX() == b.getX() - 1) {
			if(a.getY() == b.getY()) {
				return true;
			}
		}
		if(a.getX() == b.getX() + 1) {
			if(a.getY() == b.getY() - 1) {
				return true;
			}
		}
		return false;
	}
}
