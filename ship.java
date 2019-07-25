package battleShip;

import java.util.Scanner;

public class ship {
	
	static int[][] grid = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
	};
	
	static Scanner scanner = new Scanner(System.in);
	
	static int x;
	static int y;
	
	static int xAI;
	static int yAI;
	

	static int userNum = 5;
	static int AINum = 5;

	
	public static void playground(int[][] grid) {
		
		// j is x coordinate.
		// top of the playground.
		System.out.print(" ");
		for (int j = 0; j <= 9; j++) {
			System.out.print(" "+j+" ");
		}
		System.out.println();
		
		// i is y coordinate.
		// left and right sides of the playground.
		for (int y = 0; y < 10; y++) {
			// left side
			System.out.print(y);
			// between left and right
			// they are status.
			for (int x = 0; x <= 9; x++) {
				if( grid[x][y] == 0) {
				System.out.print(" . "); // nothing
				} else if (grid[x][y] == 1) {
				System.out.print(" @ "); // user ship
				} else if ( grid[x][y] == 2) {
				System.out.print(" ¥ "); // enemy ship
				} 
				else if (grid[x][y] == 3) {
					System.out.print(" ! "); // user hit enemy
				} 
				else if (grid[x][y] == 4 ) {
					System.out.print(" x "); // enemy hit user
				}
				else if (grid[x][y] == 5 ) {
					System.out.print(" + "); // enemy miss
				}
				else if (grid[x][y] == 6) {
					System.out.print(" - "); // user miss
				}
				}
			// right side 
			System.out.println("  " + y);
			
			}
		
		
		// the bottom of the playground.
		
		System.out.print(" ");
		for (int j = 0; j <= 9; j++) {
			System.out.print(" "+j+" ");
		}
		System.out.println();
		
	}
	
	public static void askFirst() {
		System.out.println("Welcome to Battle ship game!!!");
		System.out.println("Here is the playground.");
	}
	
	// at first cp ask user to decide position.
	public static void userShip() {		
		System.out.println("Where do you want to deploy your ships?");
		System.out.println("At first, both you and cp have 5 ships.");
		
		for ( int k = userNum; k > 0 ; k--) {
			System.out.printf("NO. %d", k);	
			System.out.println();
			System.out.print("tell me x coordinate. : ");
			x = scanner.nextInt();
			System.out.print("tell me y coordinate. : ");
			y = scanner.nextInt();
			
			grid[x][y] = 1;
		}
	}
	
	// cp determine the position by this code only first time.
	public static void AIShip() {

		for( int i = AINum; i > 0; i--) {
			
			do {
				xAI = (int)(Math.random() * 9);
				yAI = (int)(Math.random() * 9);
			// no to be overlapped with user's ship and the position Aiship is determined.			
			}while(grid[xAI][yAI] == 1 || grid[xAI][yAI] == 2);
			
				
			 

			System.out.printf("xAI is %d, yAI is %d", xAI, yAI);
			System.out.println();
			
			grid[xAI][yAI] = 2;
			
		}
	}
	
	// second time or later, user follow this code to decide where position it is. 
	public static void UserCheckShipSunk() {
		System.out.println("Now your turn!");
		System.out.print("tell me x coordinate. : ");
		x = scanner.nextInt();
			
		System.out.print("tell me y coordinate. : ");
		y = scanner.nextInt();
		
		if(grid[x][y] == 2) {
//			コンピューターがいた場合、沈める
			// which means the cp ship is sunk if it exist there.
			grid[x][y] = 3;
			AINum--;
		}
		if (grid[x][y] == 0) {
			grid[x][y] = 6;	
		}
		
	
	}
	// second time or later cp folow this code to decide where position it hit.
	public static void AICheckShipSunk() {
		
		
		do {
			xAI = (int)(Math.random() * 9);
			yAI = (int)(Math.random() * 9);
	// no to be overlapped with position already determined status.
		}while( grid[xAI][yAI] == 2|| grid[xAI][yAI] == 3 || grid[xAI][yAI] == 4 || grid[xAI][yAI] == 5 || grid[xAI][yAI] == 6);

		System.out.printf("xAI is %d, yAI is %d", xAI, yAI);
		System.out.println();
		
		// if there is a user ship, kill it
		if (grid[xAI][yAI] == 1) {
			grid[xAI][yAI] = 4;
			userNum--;
		} 
		// if there is nothing, that means miss.
		if ( grid[xAI][yAI] == 0) {
			grid[xAI][yAI] = 5;
		}
				
	
		}
	
	
	public static void theNumOfShip() {
		// how many ships user and cp have?
		System.out.printf("Now you have %d ships. ", userNum);
		System.out.printf("CP has %d ships.", AINum);
		System.out.println();
		
		// the number user ships lost
		if( userNum < 5) {
			System.out.printf("you lost %d", 5 - userNum);
			System.out.println();
			
			xAI = (int)(Math.random() * 9);
			yAI = (int)(Math.random() * 9);
			 
			System.out.println();
		}
		// the number cp ship lost.
		if(AINum < 5) {
			System.out.printf("CP lost %d ", 5 - AINum);
			System.out.println();
			
			
		}
	}
	

	
	public static void main(String[] args) {
		askFirst();
		playground(grid);
		userShip();
		AIShip();
		
		System.out.println("");
		playground(grid);
		theNumOfShip();
		
		
		// if either user or cp lost, can go out while loop.
		while(userNum != 0 && AINum != 0){
			
			// if user ships are gone, it doesn't happen.
			if( userNum > 0) {
		
				System.out.println("");
				UserCheckShipSunk();
				playground(grid);
				theNumOfShip();	
			}
			
			// if cp ships are gone, it doesn't happen.
			if ( AINum > 0) {
				
				System.out.println("");
				AICheckShipSunk();		
				playground(grid);
				theNumOfShip();
			}	
		}
		
		// when user ships are gone
		if ( userNum == 0) {
			System.out.println("you lost!");
		}
		
		// when cp ships are gone
		if( AINum == 0) {
			System.out.println("you won!");
		}
		
	}
}
