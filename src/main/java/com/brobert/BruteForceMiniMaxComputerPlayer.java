/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;

import com.brobert.Board.Token;

/**
 * @author brobert
 *
 */
public class BruteForceMiniMaxComputerPlayer implements ComputerPlayer {

	private Token AIType;



	public BruteForceMiniMaxComputerPlayer(Token type) {
		AIType = type;
	}





	class Move {
		Coordinate coord;
		int score;



		@Override
		public String toString() {
			return coord.toString() + " score [" + score + "]";
		}
	}



	@Override
	public Coordinate play(Board boardState) {
		List<Coordinate> legalMoves = legalMoves(boardState);
		Move bestMove = minimax(boardState, AIType, 0);
		return bestMove.coord;
	}



	/**
	 * @param board
	 * @return
	 */
	private Move minimax(Board board, Token playerType, int depth) {
		Move bestMove = new Move();
		int bestScore = (playerType == AIType) ? -1 : 1;
		bestMove.score = bestScore;
		Move currentMove;
		List<Coordinate> legalMoves = legalMoves(board);

		//game over or board is full
		if (board.enoughInARow(opposite(AIType)) || board.enoughInARow(AIType) || legalMoves.isEmpty()) {
			bestMove.score = evaluate(board);
			depth = 0;
		} else {
			//in the even that all moves are equal and none are optimal just choose the first
			bestMove.coord = legalMoves.get(0);
			//For each legal move that we can make find the best possible move -1 for minimizing and 1 for maximizing
			for (Coordinate c : legalMoves) {
				//Make this move
				board.placePiece(playerType, c.x, c.y);

				//Computer maximizing
				if (playerType == AIType) {
					currentMove = minimax(board, opposite(AIType), depth + 1);
					if (currentMove.score > bestScore) {
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;

					}

					//Human minimizing
				} else {
					currentMove = minimax(board, AIType, depth + 1);
					if (currentMove.score < bestScore) {
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;
					}
				}
				//undo move
				board.placePiece(Token.EMPTY, c.x, c.y);
			}
		}
		return bestMove;
	}



	/**
	 * @return
	 */
	private int evaluate(Board board) {
		int score = 0;
		if (board.enoughInARow(AIType)) {
			score = 1;
		}
		if (board.enoughInARow(opposite(AIType))) {
			score = -1;
		}
		return score;
	}



	/**
	 * @param player
	 * @return
	 */
	private Token opposite(Token player) {
		return (player == Token.X) ? Token.O : Token.X;
	}



	private List<Coordinate> legalMoves(Board boardState) {
		List<Coordinate> legalMoves = new ArrayList<>();
		for (int i = 0; i < boardState.getWidth(); i++) {
			for (int j = 0; j < boardState.getWidth(); j++) {
				if (boardState.at(j, i) == Token.EMPTY) {
					legalMoves.add(new Coordinate(j, i));
				}
			}
		}
		return legalMoves;
	}
}
