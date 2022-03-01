package GameBattleship.srccode;


import java.util.*;

public class Ocean {
    private Ship[][]ships = new Ship[10][10];//Used to quickly determine which ship is in any given location

    private int shotsFired; //The total number of shots fired by the user

    private int hitCount;// The number of times a shot hit a ship. If the user shoots the same part of a ship more than once, every hit is counted, even though additional “hits” don’t do the user any good.

    private int shipsSunk;//The number of ships sunk (10 ships in all)

    private Random random;
    private String indicationValue;
    

    public Ocean() {
        // Creating "empty" ocean with the EmptySea object
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
            }
        }

        // Initialzing variables
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;
        random = new Random();
       
    }

    // Helper function
    public boolean isInputLegalWithinOcean (int row, int column) {
		if (row < 0 || column <0 || row > 9 || column > 9) {
			return false;
		}
		return true;
	}

    public void placeSingleShipRandomly(Ship ship) {
		boolean isOkToPlace = false;
		int row = 10; 
		int column = 10 ; 
		boolean horizontal = false;

		while (isOkToPlace != true) {
			row = random.nextInt(10);
			column = random.nextInt(10);
			horizontal = random.nextBoolean();

			isOkToPlace = ship.okToPlaceShipAt(row, column, horizontal, this);
		}

		ship.placeShipAt(row, column, horizontal, this);	
	}

    // Place all ten ships randomly on the (initially empty) ocean. Place larger ships before smaller ones, or you may end up with no legal place to put a large ship.
    public void placeAllShipsRandomly() {

        // Create an array of ships and start with 0
        Ship[] storageShips = new Ship[10];
        storageShips[0] = new Battleship();
        storageShips[1] = new Cruiser();
        storageShips[2] = new Cruiser();
        storageShips[3] = new Destroyer();
        storageShips[4] = new Destroyer();
        storageShips[5] = new Destroyer();
        storageShips[6] = new Submarine();
        storageShips[7] = new Submarine();
        storageShips[8] = new Submarine();
        storageShips[9] = new Submarine();

        // Place all ships randomly - place larger ships first starting with 0

        for (int i=0; i<1; i++) {
			this.placeSingleShipRandomly(storageShips[i]);
		}	


    }

    
    boolean isOccupied(int row, int column) {
        // Returns true if the given location contains a ship, false if it does not
        boolean isInputLegalWithinOcean = this.isInputLegalWithinOcean(row, column);
        if (isInputLegalWithinOcean == false ) {
            return false;
		}
		else {
            if (ships[row][column] instanceof EmptySea ) {
				return false;
			}
			return true;
		}

    }

    // Returns true if the given location contains a ”real” ship, still afloat, (not an EmptySea), false if it does not. In addition, this method updates the number of shots that have been fired, and the number of hits.

    boolean shootAt(int row, int column) {
        boolean isInputLegalWithinOcean = this.isInputLegalWithinOcean(row, column);
        boolean wasHit = false;
        if (isInputLegalWithinOcean) {
			if (ships[row][column].getShipType() != "empty" && ships[row][column].isSunk()== false) {
				this.hitCount ++;
				this.shotsFired ++;
				ships[row][column].shootAt(row, column);
				wasHit = true;
			} else if (ships[row][column].getShipType() == "empty") {
				this.shotsFired ++;
				ships[row][column].shootAt(row, column);
			}
        }

        // Check if all parts are destroyed and increase sunken ships
        if (ships[row][column].toString() == "s") {
            this.shipsSunk ++;
        }
        
        return wasHit;
    }

    // Returns the number of shots fired (in the game)
    int getShotsFired() {
        return this.shotsFired;
    }

    // Returns the number of hits recorded (in the game). All hits are counted, not just the first time a given square is hit.

    int getHitCount() {
        return this.hitCount;
    }

    // Returns the number of ships sunk (in the game)
    int getShipsSunk() {
        return this.shipsSunk;
    }

    public void setShipSunk(int value) {
        this.shipsSunk += value;
    }

    // Returns true if all ships have been sunk, otherwise false
    boolean isGameOver() {
        if (this.shipsSunk == 10) {
            return true;
        } else {
            return false;
        }
    }

    // Returns the 10x10 array of Ships. 
    Ship[][] getShipArray() {
        return this.ships;
    }

    public void setShipArray(Ship ship, int column, int row) {
		this.ships[row][column] = ship;
	}

    // Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array, and column numbers should be displayed along the top. Numbers should be 0 to 9, not 1 to 10.

    void print() {
        for (int i = 0; i <= 10; i++) {
			if (i == 0) {
                // Header
				System.out.print("  0 1 2 3 4 5 6 7 8 9");
			}
			else { 
				System.out.print(i - 1 + " ");
				for (int j = 0; j < 10; j++) {
					Ship ship = this.ships[i-1][j];
					if (ship.getShipType() == "empty") {
						System.out.print(ship + " ");
					}
					else if (ship.isHorizontal() == true) {
						if (ship.getHit()[j - ship.getBowColumn()] == true ) {
							System.out.print(ship + " ");	
						}
						else {
							System.out.print(ship + " ");
						}
					}
					else if (ship.isHorizontal() == false ) {
						if (ship.getHit()[i - 1 - ship.getBowRow()] == true) {
							System.out.print(ship + " ");
						}	
						else {
							System.out.print("-" + " ");
						}
					}
				}
			}
			System.out.println();
		}
    }

    void printWithShips()  {
        for (int i = 0; i <= 10; i++) {
			if (i == 0) {
                // Header
				System.out.print("  0 1 2 3 4 5 6 7 8 9");
			}
			else {
                System.out.print(i-1+" ");
				for (int j = 0; j < 10; j++) {
                    Ship ship = this.ships[i-1][j];
                    if (ship.getShipType() == "battleship") {
                        indicationValue = "b";
                    }
                    else if (ship.getShipType() == "cruiser") {
                        indicationValue = "c";
                    }
                    else if (ship.getShipType() == "destroyer") {
                        indicationValue = "d";
                    }
                    else if (ship.getShipType() == "submarine") {
                        indicationValue = "s";
                    }
;
					if (ship.getShipType()=="empty") {
						System.out.print(ship + " ");
					}
					else if (ship.isHorizontal() == true) {
						if (ship.getHit()[j - ship.getBowColumn()] == true) {
							System.out.print(indicationValue + " ");	
						}
						else {
							System.out.print(indicationValue + " ");
						}
					}
					else if (ship.isHorizontal() == false ) {
						if (ship.getHit()[i - 1 - ship.getBowRow()] == true) {
							System.out.print(indicationValue + " ");
						}	
						else {
							System.out.print(indicationValue + " ");
						}
					}
                }
            }
            System.out.println();
        }
    }
}
