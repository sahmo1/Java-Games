package GameBattleship.BattleshipTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	private Ship battleship;
	private Ship cruiser;
	private Ship destroyer;
	private Ship submarine;
	private Ship emptySea;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
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
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(0, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//TODO
		//More tests

		// Unit tests fpr placing battleship 
		battleship.placeShipAt(4, 3, true, ocean);
		battleship.placeShipAt(5, 4, true, ocean);
		battleship.placeShipAt(6, 5, true, ocean);
		battleship.placeShipAt(7, 6, true, ocean);


		// Unit tests when occupied 
		assertTrue(ocean.isOccupied(4, 1));
		assertTrue(ocean.isOccupied(4, 2));
		assertTrue(ocean.isOccupied(4, 3));
		assertTrue(ocean.isOccupied(4, 5));

		// Unit tests when index is out of range
		assertFalse(ocean.isOccupied(-3, 0));
		assertFalse(ocean.isOccupied(0, -4));
		assertFalse(ocean.isOccupied(10, 0));
		assertFalse(ocean.isOccupied(0, 20));


		// Unit tests when not occupied 
		assertFalse(ocean.isOccupied(3, 5));
		assertFalse(ocean.isOccupied(3, 6));
		assertFalse(ocean.isOccupied(3, 7));
		assertFalse(ocean.isOccupied(3, 9));
		assertFalse(ocean.isOccupied(3, 9));


	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		
		//TODO
		//More tests

		// 1: Placing Battleship 
		battleship.placeShipAt(4, 6, true, ocean);

		//2: when ship is sunk 
		assertTrue(battleship.isSunk());
		assertFalse(ocean.shootAt(8, 8));

		// 3: shooting the ship
		assertTrue(ocean.shootAt(5, 9));
		assertTrue(ocean.shootAt(6, 0));
		assertTrue(ocean.shootAt(7, 1));
		assertTrue(ocean.shootAt(8, 2));

		//4: missed shots 
		assertFalse(ocean.shootAt(2, 3));
		assertFalse(ocean.shootAt(3, 4));
		assertFalse(ocean.shootAt(4, 5));
		assertFalse(ocean.shootAt(5, 6));

		




	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//TODO
		//More tests

		//1: when submarine is sunked 
		ocean.shootAt(5,5);
		assertEquals(6, ocean.getShotsFired());

		//2: invalid shot
		ocean.shootAt(10,10); 
		assertEquals(0, ocean.getShotsFired());

		//3: shooting at the same row/column multiple times. testing to make sure it counts. 
		ocean.shootAt(7,7);
		ocean.shootAt(7,7);
		assertEquals(4, ocean.getShotsFired());



	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//TODO
		//More tests

		//1: shooting battleship 
		ocean.shootAt(2,3);
		ocean.shootAt(2,4);
		assertEquals(5, ocean.getHitCount());

		//2: shooting an empty sea
		ocean.shootAt(2, 2);
		assertEquals(9, ocean.getHitCount());

		//3: shooting a sunk ship 
		ocean.shootAt(1, 1);
		assertEquals(8, ocean.getHitCount());

		//4: Shooting the Cruiser 
		ocean.shootAt(2 ,0);
		assertEquals(2, ocean.getHitCount());


	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//TODO
		//More tests

		//1: Sunking the submarine 
		ocean.shootAt(2, 3);
		assertEquals(1, ocean.getShipsSunk());

		//2: Ship is not sunked
		assertEquals(0, ocean.getShipsSunk());

		//3: All the ships are sunked 
		ocean.shootAt(6,1);
		ocean.shootAt(6,5);
		ocean.shootAt(5,5);
		assertEquals(5, ocean.getShipsSunk());




	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//TODO
		//More tests
		battleship.placeShipAt(4, 6, true, ocean);
		assertEquals("battleship", ocean.getShipArray()[4][6].getShipType());
		assertEquals("battleship", ocean.getShipArray()[4][7].getShipType());
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals("empty", ocean.getShipArray()[5][5].getShipType());
		





	}

}
