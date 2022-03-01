package GameBattleship.srccode;

/**
 * The abstract Ship class has the instance variables below.
Note: Fields should be declared private unless there is a good reason for not doing so. This is known as “encapsulation”, which is the process of making the fields in a class private and providing access to the fields via public methods (e.g. getters and setters).
 */
public abstract class Ship {
    private Ocean ocean;
    private int bowRow; //The row that contains the bow (front part of the ship)
    private int bowColumn; //The column that contains the bow (front part of the ship)
    private int length; //The length of the ship
    private boolean horizontal; //A boolean that represents whether the ship is going to be placed horizontally or vertically
    private boolean[] hit; //An array of booleans that indicate whether that part of the ship has been hit or not

    /**
     * This constructor sets the length property of the particular ship and initializes the hit array based on that length
     */
    public Ship(int length) {
        this.length = length;
        this.hit = new boolean[length];
    }

    // Defining Getters 

    //Returns the ship length
    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    //Returns the row corresponding to the position of the bow

    public int getBowRow() {
        return this.bowRow;
    }
    //Returnsthebowcolumnlocation
    public int getBowColumn() {
        return this.bowColumn;
    }

    //Returns the hit array
    public boolean[] getHit() {
        return this.hit;
    }

    //Returns whether the ship is horizontal or not
    public boolean isHorizontal() {
        return this.horizontal;
    }

    // Defining Setters

    //Sets the value of bowRow
    public void setBowRow(int row) {
        this.bowRow = row;
    }

    //Sets the value of bowColumn
    public void setBowColumn(int column) {
        this.bowColumn = column;
    }

    //Sets the value of the instance variable horizontal
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    //Returns the type of ship as a String. Every specific type of Ship (e.g.BattleShip, Cruiser, etc.) has to override and implement this method and return the corresponding ship type.
    public abstract String getShipType();

    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        /*
        Based on the given row, column, and orientation, returns true if it is okay to put a
        ship of this length with its bow in this location; false otherwise. The ship must not
        overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
        and it must not ”stick out” beyond the array. Does not actually change either the
        ship or the Ocean - it just says if it is legal to do so. 
        */
      
        boolean isOkToPlace = true;
        
        // Vertical ships else horizontal
        if (horizontal == false) {
            // Check if the ship is out of bounds
            if (row + this.length > 10) {
                isOkToPlace = false;
            }
            // Check if the ship is overlapping another ship or touching it
            else {
                for (int i = 0; i < this.length; i++) {
                    if (ocean.isOccupied(row + i, column)) {
                        isOkToPlace = false;
                    }
                }
            }
        } else {
            // Check if the ship is out of bounds
            if (column + this.length > 10) {
                isOkToPlace = false;
            }
            // Check if the ship is overlapping another ship
            else {
                for (int i = 0; i < this.length; i++) {
                    if (ocean.isOccupied(row, column + i)) {
                        isOkToPlace = false;
                    }
                }
            }
        }

        return isOkToPlace;
    }


    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);

        // Place the ship on the ocean
        if (horizontal == false) {
            for (int i = 0; i < this.length; i++) {
                ocean.setShipArray(this, i, row);
            }
        } else {
            for (int i = 0; i < this.length; i++) {
                ocean.setShipArray(this, column + i, row);
            }
        }
    }

    boolean shootAt(int row, int column) {
        /*
        If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, mark that part of the ship as “hit” (in the hit array, index 0 indicates the
        bow) and return true; otherwise return false.
        */

        int sectionOfShip = 0;
        boolean isOccupied = false;

		
		if (this.horizontal == false && this.bowColumn == column) {
			for (int i = this.bowRow; i < this.bowRow + this.length; i++) {
				if (row == i) {
					sectionOfShip = i - this.bowRow;
					isOccupied = true;
					break;
				}
			}
		} else if (this.horizontal == true && this.bowRow == row) {
			for (int i = this.bowColumn; i < this.bowColumn + this.length; i++) {
				if (column == i) {
                    isOccupied = true;
					sectionOfShip = i - this.bowColumn;
					break;
				}
			}
		}

		if (this.isSunk() == false  && isOccupied == true ) {
            this.hit[sectionOfShip] = true;
			return true;
		}


		return false;

    }

    
    boolean isSunk() {
        for (boolean b : hit) if (!b) return false;
		return true;
    }

    @Override
    public String toString() {
        /*
        Returns a single-character String to use in the Ocean’s print method. This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk. This
        method can be used to print out locations in the ocean that have been shot at; it
        should not be used to print locations that have not been shot at. Since toString
        behaves exactly the same for all ship types, it is placed here in the Ship class.
        */

        // Check if ship was hit by the shot

        // if this.hit array contains true continue with the loop
        for (boolean b : hit) {
            if (b == true) {
                if (this.isSunk()) {
                    return "s";
                } else {
                    return "x";
                }
            }
        }
        return "-";
    }
}

