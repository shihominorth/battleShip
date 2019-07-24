package battleShip;


import java.util.ArrayList;
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
		// left and right of the playground.
		for (int y = 0; y < 10; y++) {
			System.out.print(y);
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
					System.out.println(" - "); // user miss
				}
				}
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
	
	public static void userShip() {		
		System.out.println("Where do you want to deploy your ships?");
		System.out.println("At first, both you and cp have 5 ships.");
		
		//ship myObj = new ship();
		
		
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
	
	public static void AIShip() {

		for( int i = AINum; i > 0; i--) {
//			xAI = (int)(Math.random() * 9);
//			yAI = (int)(Math.random() * 9);
			
			do {
				xAI = (int)(Math.random() * 9);
				yAI = (int)(Math.random() * 9);
					
			}while( grid[xAI][yAI] == 1);

			//System.out.printf("xAI is %d, yAI is %d", xAI, yAI);
			//System.out.println();
			
			grid[xAI][yAI] = 2;
			
		}
	}
	
	public static void UserCheckShipSunk() {
		System.out.println("Now your turn!");
		System.out.print("tell me x coordinate. : ");
		x = scanner.nextInt();
			
		System.out.print("tell me y coordinate. : ");
		y = scanner.nextInt();
		
		if(grid[x][y] == 2) {
//			コンピューターがいた場合、沈める
			grid[x][y] = 3;
			AINum--;
		}
	
	}
	
	public static void AICheckShipSunk() {
		
		//int xAI = (int)(Math.random() * 9);
		//int yAI = (int)(Math.random() * 9);
		
		do {
			xAI = (int)(Math.random() * 9);
			yAI = (int)(Math.random() * 9);
				
		}while( grid[xAI][yAI] == 4|| grid[xAI][yAI] == 2 || grid[xAI][yAI] == 5);

		System.out.printf("xAI is %d, yAI is %d", xAI, yAI);
		System.out.println();
		
		if (grid[xAI][yAI] == 1) {
			grid[x][y] = 4;
			userNum--;
		}
			grid[xAI][yAI] = 5;	
	
		}
	
	
	public static void theNumOfShip() {
		System.out.printf("Now you have %d ships. ", userNum);
		System.out.printf("CP has %d ships.", AINum);
		System.out.println();
		
		if( userNum < 5) {
			System.out.printf("you lost %d", 5 - userNum);
			System.out.println();
			
			int xAI = (int)(Math.random() * 9);
			int yAI = (int)(Math.random() * 9);
			
//			if ( grid[xAI][yAI] = 1; )
//			grid[xAI][yAI] = 2;
			
			
			 
			System.out.println();
		}
		if(AINum < 5) {
			System.out.printf(" CP lost %d ", 5 - AINum);
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
		
		while( userNum > 0 && AINum > 0) {
			System.out.println("");
			UserCheckShipSunk();
			playground(grid);
			theNumOfShip();
			
			System.out.println("");
			AICheckShipSunk();		
			playground(grid);
			theNumOfShip();
			
		}
		if ( userNum == 0) {
			System.out.println("you lost!");
		}
		if( AINum == 0) {
			System.out.println("cp lost!");
		}
		
	}
}
