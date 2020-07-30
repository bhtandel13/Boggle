//package boggle;

import static org.junit.Assert.*;
import java.awt.Point;
import org.junit.Test;

public class TestRunner {

	private Board testBoard;

	@Test
	public void testBoard() {
		testBoard = new Board();
		testBoard.printBoard();
		//assertNotNull(testBoard);
	}

	@Test
	public void testNearbyPiece() {
		testBoard = new Board();
		Point a = new Point(1, 1);
		assertTrue(testBoard.nearbyPiece(a, new Point(0, 0)));
		assertTrue(testBoard.nearbyPiece(a, new Point(0, 1)));
		assertTrue(testBoard.nearbyPiece(a, new Point(0, 2)));
		assertFalse(testBoard.nearbyPiece(a, new Point(0, 3)));
		assertTrue(testBoard.nearbyPiece(a, new Point(1, 0)));
		assertTrue(testBoard.nearbyPiece(a, new Point(1, 2)));
		assertFalse(testBoard.nearbyPiece(a, new Point(1, 3)));
		assertTrue(testBoard.nearbyPiece(a, new Point(2, 0)));
		assertTrue(testBoard.nearbyPiece(a, new Point(2, 1)));
		assertTrue(testBoard.nearbyPiece(a, new Point(2, 2)));
		assertFalse(testBoard.nearbyPiece(a, new Point(2, 3)));
		assertFalse(testBoard.nearbyPiece(a, new Point(3, 0)));
		assertFalse(testBoard.nearbyPiece(a, new Point(3, 1)));
		assertFalse(testBoard.nearbyPiece(a, new Point(3, 2)));
		assertFalse(testBoard.nearbyPiece(a, new Point(3, 3)));
	}
}
