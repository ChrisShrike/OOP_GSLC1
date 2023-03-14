package main;

import java.util.Scanner;
import java.util.Random;

public class MainModule {
	public static void main(String args[]) {
		// declare certain arrays to be used in the program
		boolean[][] treasure = new boolean[5][5];
		boolean[][] board = new boolean[5][5];
		boolean[][] tag = new boolean[5][5];
		boolean gameOver = false;
		int row = 0;
		int col = 0;
		int maxPoints = 0;
		Scanner myObj = new Scanner(System.in);
		Random num = new Random();
		
		// for loop 2D array to make sure that all values are false
		for(int i = 0; i <  5; i++) {
			for(int j = 0; j <  5; j++) {
				treasure[i][j] = false;
				tag[i][j] = false;
			}
		}
		
		// for loop to generate the treasure location in random rows and columns
		for(int i = 0; i < 5; i++) {
			treasure[num.nextInt(5)][num.nextInt(5)] = true;
		}
		
		// for loop to count how many treasures inside the tiles
		for(int i = 0; i <  5; i++) {
			for(int j = 0; j <  5; j++) {
				if(treasure[i][j] == true) {
					maxPoints += 1;
				}
			}
		}
		
		// first menu display
		System.out.print("    0  1  2  3  4");
		for(int i = 0; i < 5; i++) {
			System.out.println();
			System.out.print(" " + i+ " ");
			for(int j = 0; j < 5; j++) {
				System.out.print(" ? ");
			}
		}
		
		// while loop to loop the program until the game is over
		while(gameOver == false) {
			System.out.println();
			System.out.println();
			System.out.println("Treasure(s) left: " + maxPoints);
			System.out.println();
			
			System.out.println("What row do you want to search? (0 - 4)");
			row = myObj.nextInt();
			
			// while loop to make sure the inputs are correct
			while(row < 0 || row > 4) {
				System.out.println("ERROR403: wrong input!");
				System.out.println("What row do you want to search? (0 - 4)");
				row = myObj.nextInt();
			}
			
			System.out.println("What column do you want to search? (0 - 4)");
			col = myObj.nextInt();
			while(col < 0 || col > 4) {
				System.out.println("ERROR403: wrong input!");
				System.out.println("What column do you want to search? (0 - 4)");
				col = myObj.nextInt();
			}
			
			// to keep the coordinates from the input
			board[row][col] = true;
			
			// to call the upBoard function
			updBoard(board, treasure, tag);
			
			// if function to change the treasure coordinates to false because the treasure
			// had been collected
			if(treasure[row][col]) {
				maxPoints -= 1;
				treasure[row][col] = false;
			}
			
			// if function to be used when all the treasures had been collected
			if(maxPoints == 0) {
				System.out.println();
				System.out.println("◝(ᵔᵕᵔ)◜ All treasure(s) are found!!! ◝(ᵔᵕᵔ)◜");
				gameOver = true;
			}
		}
	}
	
	// upBoard function to update the board with the current inputs whether the coordinates have 
	// the treasure
	public static void updBoard(boolean[][] upBoard, boolean[][] treasure, boolean[][] tagged) {
		System.out.print("    0  1  2  3  4");
		for(int i = 0; i < 5; i++) {
			System.out.println();
			System.out.print(" " + i+ " ");
			for(int j = 0; j < 5; j++) {
				if(upBoard[i][j] == true && treasure[i][j] == true && tagged[i][j] == false) {
					tagged[i][j] = true;
					System.out.print(" V ");
				}
				else if(upBoard[i][j] == true && treasure[i][j] == false && tagged[i][j] == true) {
					System.out.print(" V ");
				}
				else if(upBoard[i][j] == true && treasure[i][j] == false) {
					System.out.print(" X ");
				}
				else {
					System.out.print(" ? ");
				}
			}
		}
	}
}
