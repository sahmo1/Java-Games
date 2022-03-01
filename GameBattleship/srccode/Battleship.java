package GameBattleship.srccode;

/**
 * Describes a ship of length 4

 */
class Battleship extends Ship {
    public Battleship() {
        super(4);
    }

    @Override
	public String getShipType() {
		return "battleship";
	}

}

