/**
 *
 */
package com.brobert;

import java.util.Scanner;

import com.brobert.TwoDimensionalBoard.Token;

/**
 * @author brobert
 *
 */
public class TicTacToeGame extends Game {

	private int turns = 0;

	private String first;



	public TicTacToeGame(Args args) {
		setBoard(new TicTacToeBoard(args.getWidth()));
		human(args.getHuman());
		first = args.getFirst();
	}



	public void placePiece(Token type, Coordinate coord) {
		getBoard().placePiece(coord.x, coord.y, type);
		turns++;
	}



	@Override
	public void start() {

		ComputerPlayer player = new BruteForceMiniMaxComputerPlayer(getComputer());
		Scanner scanner = new Scanner(System.in);
		System.out.println("Starting Game...");
		System.out.println("Human is [" + getHuman() + "]");
		System.out.println("Computer is [" + getComputer() + "]");

		System.out.println();
		getBoard().printBoard();
		System.out.println();

		while (!isOver()) {
			if (first.equalsIgnoreCase("human")) {

				playerTurn(scanner);
				if (!isOver()) {
					computerTurn(player);
				}
			} else if (first.equalsIgnoreCase("computer")) {
				computerTurn(player);
				if (!isOver()) {
					playerTurn(scanner);
				}
			}

		}
	}



	@Override
	public void printTurn(Token square, Coordinate coordinate) {
		System.out.println("\n" + getPlayerBySquare(square) + "'s turn. Turn #" + turns);
		System.out.println(getPlayerBySquare(square) + " placed " + square + " at " + coordinate.huamnFriendlyString());
		getBoard().printBoard();
		System.out.print("\n\n");
	}



	@Override
	public void playerTurn(Scanner scanner) {
		System.out.println("Enter X:");
		int x = getInt(scanner);
		System.out.println("Enter Y:");
		int y = getInt(scanner);
		while (!isValidCoordinate(x, y)) {
			System.out.println("Invalid coordinate... Please try again.");
			System.out.println("Enter X: ");
			x = getInt(scanner);
			System.out.println("Enter Y: ");
			y = getInt(scanner);
		}
		Coordinate play = new Coordinate(x - 1, y - 1);
		getBoard().placePiece(play.x, play.y, getHuman());
		printTurn(getHuman(), play);
	}



	/**
	 * @param square
	 * @return
	 */
	private String getPlayerBySquare(Token square) {
		if (getHuman() == square) {
			return "Human";
		} else {
			return "Computer";
		}
	}



	/**
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	public boolean isValidCoordinate(int x, int y) {
		return getBoard().at(x - 1, y - 1) == Token.EMPTY;
	}

}
