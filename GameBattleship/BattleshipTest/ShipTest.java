package GameBattleship.BattleshipTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;

	private Ship battleship;
	private Ship cruiser;
	private Ship destroyer;
	private Ship submarine;
	private Ship emptySea;

	private int bowRow;
	private int bowColumn;
	private boolean horizontal;
	protected int length;
	protected boolean [] hit = new boolean [4];


	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
		battleship = new Battleship();
		destroyer = new Destroyer();
		cruiser = new Cruiser();
		submarine = new Submarine();
		emptySea = new EmptySea();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		//TODO
		//More tests

		//1: submarine ship
		assertEquals(1, submarine.getLength());

		//2: destroyer ship
		assertEquals(2, destroyer.getLength());

		//3: cruiser ship
		assertEquals(3, cruiser.getLength());

		//4: battleship ship
		assertEquals(4, battleship.getLength());

	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests

		// 1: submarine 
		assertEquals(this.bowColumn, submarine.getBowColumn());

		// 2: destroyer 
		assertEquals(this.bowColumn, destroyer.getBowColumn());

		// 3: cruiser 
		assertEquals(this.bowColumn, cruiser.getBowColumn());

		// 4: battleship 
		assertEquals(this.bowColumn, battleship.getBowColumn());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//TODO
		//More tests

		// 1: submarine 
		submarine.setBowColumn(4);
		assertEquals(4, submarine.getBowColumn());

		// 2: destroyer 
		destroyer.setBowColumn(3);
		assertEquals(3, destroyer.getBowColumn());

		// 3: cruiser 
		cruiser.setBowColumn(2);
		assertEquals(2, cruiser.getBowColumn());

		// 4: battleship 
		battleship.setBowColumn(1);
		assertEquals(1, battleship.getBowColumn());

	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//TODO
		//More tests

		assertEquals(hit[2], battleship.getHit()[2]);
		assertEquals(hit[3], battleship.getHit()[3]);
		assertEquals(hit[4], battleship.getHit()[4]);

	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//TODO
		//More tests

		//1: submarine 
		assertEquals("submarine", submarine.getShipType());

		//2: destroyer
		assertEquals("destroyer", destroyer.getShipType());

		//3: cruiser
		assertEquals("cruiser", cruiser.getShipType());

		//4: Battleship   
		assertEquals("battleship", battleship.getShipType());

		//5: empty 
		assertEquals("empty", emptySea.getShipType());
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests	

		//1: submarine 
		assertEquals(this.horizontal, submarine.isHorizontal());

		//2: destroyer  
		assertEquals(this.horizontal, destroyer.isHorizontal());

		//3: cruiser 
		assertEquals(this.horizontal, cruiser.isHorizontal());

		//4: battleship 
		assertEquals(this.horizontal, battleship.isHorizontal());

		//5: empty sea 
		assertEquals(this.horizontal, emptySea.isHorizontal());

	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//TODO
		//More tests
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//TODO
		//More tests

		//1: battleship 
		battleship.setBowRow(1);
		assertEquals(1, battleship.getBowRow());

		//2: cruiser 
		cruiser.setBowRow(2);
		assertEquals(2, cruiser.getBowRow());

		//3: destroyer    
		destroyer.setBowRow(3);
		assertEquals(3, destroyer.getBowRow());

		//4 submarine    
		submarine.setBowRow(4);
		assertEquals(4, submarine.getBowRow());

		//5: emptySea
		emptySea.setBowRow(5);
		assertEquals(5, emptySea.getBowRow());

	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//TODO
		//More tests

		//1: empty sea
		emptySea.setHorizontal(false);
		assertFalse(emptySea.isHorizontal());

		//2: submarine
		submarine.setHorizontal(false);
		assertFalse(submarine.isHorizontal());

		//3: destroyer
		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());

		//4: cruiser 
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());

		//5:  battleship 
		battleship.setHorizontal(true);
		assertTrue(battleship.isHorizontal());

	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//TODO
		//More tests

		//1: vertical position
		assertFalse(submarine.okToPlaceShipAt(2, 2, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(4, 1, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(3, 0, false, ocean));
		assertFalse(battleship.okToPlaceShipAt(1, 5, false, ocean));

		//2: horizontal position 
		assertFalse(submarine.okToPlaceShipAt(3, 1, true, ocean));
		assertFalse(cruiser.okToPlaceShipAt(1, 3, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(3, 0, true, ocean));

		//3: diagonally position
		assertFalse(submarine.okToPlaceShipAt(2, 1, false, ocean));
		assertFalse(submarine.okToPlaceShipAt(1, 3, false, ocean));
		assertFalse(cruiser.okToPlaceShipAt(3, 0, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(0, 3, false, ocean));
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//TODO
		//More tests


	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);
		

		//TODO
		//More tests

		//1: 

		assertFalse(ocean.isOccupied(4, 4));
		battleship.placeShipAt(4, 4, true, ocean);
		assertFalse(ocean.isOccupied(6, 5));
		assertTrue(ocean.isOccupied(4, 4));
		
		//2:   
		assertFalse(ocean.isOccupied(1, 1));
		battleship.placeShipAt(1, 1, true, ocean);
		assertFalse(ocean.isOccupied(6, 5));
		assertTrue(ocean.isOccupied(1, 1));
		
		//3:  
		assertFalse(ocean.isOccupied(0, 0));
		battleship.placeShipAt(0, 0, true, ocean);
		assertFalse(ocean.isOccupied(1, 5));
		assertTrue(ocean.isOccupied(0, 0));

	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests

		//1: placing and shooting a ship 
		battleship.placeShipAt(5, 9, true, ocean);

		//2: empty sea return false 
		assertFalse(emptySea.shootAt(2,2));

		//3: successful shoot  
		assertTrue(battleship.shootAt(1,1));
		assertTrue(battleship.shootAt(1,2));
		assertTrue(battleship.shootAt(1,3));

		//4: failure shoot 
		assertFalse(battleship.shootAt(2,9));
		assertFalse(battleship.shootAt(0,0));
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());
		
		//TODO
		//More tests
		
		//1:   empty sea is sunked 
		assertFalse(emptySea.isSunk());
		
		// 2: Ship is not sunked 
		assertFalse(battleship.isSunk());
		battleship.shootAt(4,6);
		battleship.shootAt(4,7);

		//3: Ship is sunked 
		assertTrue(battleship.isSunk());

		    

	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("-", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());
		
		//TODO
		//More tests

		//1: Ship 
		submarine.placeShipAt(1, 1, true, ocean);
		assertEquals(".", submarine.toString());  // treat as empty if it's not shot
		submarine.shootAt(1,1);
		submarine.shootAt(1,2);
		assertEquals("x", submarine.toString()); // shot but is not sunk yet
		submarine.shootAt(1,3);
		assertEquals("s", submarine.toString()); // print x if the ship is sunk

		//2: Cruiser 
		cruiser.placeShipAt(3, 3, true, ocean);
		assertEquals(".", cruiser.toString());  // treat as empty if it's not shot
		cruiser.shootAt(3,3);
		cruiser.shootAt(3,4);
		assertEquals("x", cruiser.toString()); // shot but is not sunk yet
		cruiser.shootAt(3,7);
		assertEquals("s", cruiser.toString()); // print x if the ship is sunk


		//3: Empty Sea
		assertEquals(".", emptySea.toString()); 
		emptySea.shootAt(2,2);
		assertEquals("-", emptySea.toString()); 

		
	}

}
