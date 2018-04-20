/**
 *
 */
package com.brobert;

import com.brobert.BoardState.Token;

/**
 * @author brobert
 *
 */
public class Game {

	private BoardState boardState;

	private Token human, computer;

	private int turns = 0;



	public Game(Args args) {
		boardState = new BoardState(args.getWidth());
		human(args.getHuman());
	}



	public void placePiece(Token type, Coordinate coord) {
		boardState.placePiece(type, coord.x, coord.y);
		turns++;
	}



	public BoardState getBoardState() {
		return BoardState.copyOf(boardState);
	}



	public void printTurn(Token square, Coordinate coordinate) {
		System.out.println("\n" + getPlayerBySquare(square) + "'s turn. Turn #" + turns);
		System.out.println(getPlayerBySquare(square) + " placed " + square + " at " + coordinate.toString());
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
			System.exit(0);
			return true;
		}
		if (boardState.enoughInARow(human)) {
			System.out.println("Human Won!");
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
