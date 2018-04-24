/**
 *
 */
package com.brobert;

import com.brobert.Board.Token;

/**
 * @author brobert
 *
 */
public class Game {

	private Board boardState;

	private Token human, computer;

	private int turns = 0;



	public Game(Args args) {
		boardState = new Board(args.getWidth());
		human(args.getHuman());
	}



	public void placePiece(Token type, Coordinate coord) {
		boardState.placePiece(type, coord.x, coord.y);
		turns++;
	}



	public Board getBoardState() {
		return Board.copyOf(boardState);
	}



	public void printTurn(Token square, Coordinate coordinate) {
		System.out.println("\n" + getPlayerBySquare(square) + "'s turn. Turn #" + turns);
		System.out.println(getPlayerBySquare(square) + " placed " + square + " at " + coordinate.huamnFriendlyString());
		boardState.printBoard();
		System.out.print("\n\n");
	}



	/**
	 * @param square
	 * @return
	 */
	private String getPlayerBySquare(Token square) {
		if (human == square) {
			return "Human";
		} else {
			return "Computer";
		}
	}



	/**
	 * @param humanType
	 */
	private void human(Token humanType) {
		if (humanType == null) {
			throw new IllegalArgumentException("Human cant play empty type.");
		}
		human = humanType;
		if (humanType == Token.X) {
			computer = Token.O;
		} else {
			computer = Token.X;
		}
	}



	public Token getHuman() {
		return human;
	}



	public Token getComputer() {
		return computer;
	}



	/**
	 * @return
	 */
	public boolean isOver() {
		if (boardState.enoughInARow(computer)) {
			System.out.println("Computer Won!");
			boolean humanWon = boardState.enoughInARow(computer);
			System.exit(0);
			return true;
		}
		if (boardState.enoughInARow(human)) {
			System.out.println("Human Won!");
			boolean humanWon = boardState.enoughInARow(human);
			System.exit(0);
			return true;
		}
		if (boardState.isFull()) {
			System.out.println("Stalemate.");
			System.exit(0);
			return true;
		}
		return false;
	}

}
