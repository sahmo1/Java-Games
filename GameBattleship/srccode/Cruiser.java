package GameBattleship.srccode;

// Describes a ship of length 3
class Cruiser extends Ship {
    public Cruiser() {
        super(3);
    }

    @Override
	public String getShipType() {
		return "cruiser";
	}

}