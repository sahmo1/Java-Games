package GameBattleship.srccode;


// Describes a ship of length 2
class Destroyer extends Ship {
    public Destroyer() {
        super(2);
    }

    @Override
	public String getShipType() {
		return "destroyer";
	}

}