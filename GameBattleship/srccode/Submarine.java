package GameBattleship.srccode;


// Describes a ship of length 1
class Submarine extends Ship {
    public Submarine() {
        super(1);
    }

    @Override
	public String getShipType() {
		return "submarine";
	}

}