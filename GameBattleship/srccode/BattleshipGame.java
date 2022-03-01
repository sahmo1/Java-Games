package GameBattleship.srccode;

import java.util.*;

public class BattleshipGame {
    private Ocean ocean;
	private boolean gameover;
	private boolean running;

    public BattleshipGame() {
		// create an ocean object
		this.ocean = new Ocean();
		// place the ships randomly
		this.ocean.placeAllShipsRandomly();

		this.gameover = false;
		this.running = true;
	}
	
	public static void main(String[] args) {
		BattleshipGame game = new BattleshipGame();
		game.run();
	}

	public int[] stringToInt(String inputString) {
		int[] numArray =  new int[2]; 
		String[] string = {};
		if (inputString.length() == 3 ) {				
			for (int i = 0; i < 2; i++) {
				try {
					string = inputString.split(",");
					int num = Integer.parseInt(string[i]);
					numArray[i] = num;	
				}
				catch (NumberFormatException e) {
					numArray[0] = 1337; 
				}
				catch (ArrayIndexOutOfBoundsException e) {
					numArray[0] = 1337;
				}
			}
		}
		else {
			numArray[0] = 1337; 
		}
		return numArray;
	}

	public String newScanner() {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}

	public void run() {
		while (this.running) {
			System.out.println("Welcome to the Battleship Game.\n"
			+ "To shoot type x,y - for instance 1,3 if you want to show column 1 and row 3. \n"
		);
			this.ocean.print();
      this.ocean.printWithShips();

			while (this.gameover == false) { 	
				System.out.println("Please enter a command:");
				String command = this.newScanner();

				if (command.toLowerCase().equals("quit")) {
					this.gameover = true;
					System.out.println("Goodbye.");
					this.running = false;
				}

				else {
					int[] position = stringToInt(command);

					if (position[0] == 1337) {
						System.out.println("This is not a valid input\n");
					}

					else {
						ocean.shootAt(position[0],  position[1]);
						ocean.print();
						this.gameover = ocean.isGameOver();


						if (this.gameover == true) {

							System.out.println("Congratulations you sunk all ships!\n"
									+ "You required " + ocean.getShotsFired() + " shots.");

							System.out.println("Want to play again? Type y for yes");
							String input = this.newScanner();
							if (input.equals("y")) {
								this.gameover = false;
								System.out.println("Game restart");

								this.ocean = new Ocean();

								this.ocean.placeAllShipsRandomly();

								this.ocean.print();			
							}
							else {
								System.out.println("Goodbye.");
								this.running = false;
							}

						}
						else {	
							System.out.println("Sunken Ships: " + ocean.getShipsSunk());
						}
					}
				}
			} 
		}
	}
}
