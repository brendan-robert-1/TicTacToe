/**
 *
 */
package com.brobert;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.brobert.Board.Token;

/**
 * @author brobert
 *
 */
public class TTTGameRunner {

	private ComputerPlayer player;

	private Game game;



	public static void main(String[] argsv) {
		TTTGameRunner gameRunner = new TTTGameRunner();
		Args args = new Args();
		JCommander.newBuilder().addObject(args).build().parse(argsv);
		gameRunner.start(args);

	}



	/**
	 *
	 */
	private void start(Args args) {
		game = new Game(args);
		player = new BruteForceMiniMaxComputerPlayer(game.getComputer());
		Scanner scanner = new Scanner(System.in);
		System.out.println("Starting Game...");
		System.out.println("Human is [" + game.getHuman() + "]");
		System.out.println("Computer is [" + game.getComputer() + "]");
		while (!game.isOver()) {
			computerTurn();
			if (!game.isOver()) {
				playerTurn(scanner);
			}
		}
	}



	/**
	 *
	 */
	private void computerTurn() {
		Coordinate computerPlay = player.play(game.getBoardState());
		game.placePiece(game.getComputer(), computerPlay);
		game.printTurn(game.getComputer(), computerPlay);
	}



	/**
	 *
	 */
	private void playerTurn(Scanner scanner) {
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
		Coordinate play = new Coordinate(x, y);
		game.placePiece(game.getHuman(), play);
		game.printTurn(game.getHuman(), play);
	}



	private int getInt(Scanner scanner) {
		boolean isValid = false;
		int entry = 0;
		while (isValid == false) {
			try {
				entry = scanner.nextInt();
				isValid = true;
				if (entry > (game.getBoardState().getWidth() - 1)) {
					isValid = false;
					System.out.println("Integer is out of bounds of the width of the board: " + game.getBoardState().getWidth());
				}
			} catch (InputMismatchException e) {
				System.out.println("Invalid integer please try again...");
				scanner.next();
				isValid = false;
			}
		}
		return entry;

	}



	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isValidCoordinate(int x, int y) {
		return game.getBoardState().at(x, y) == Token.EMPTY;
	}

}
