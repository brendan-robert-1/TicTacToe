/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;

import com.brobert.BoardState.Token;

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
	}



	@Override
	public Coordinate play(BoardState boardState) {
		List<Coordinate> legalMoves = legalMoves(boardState);
		Move bestMove = minimax(boardState, AIType, 0);
		return bestMove.coord;
	}



	/**
	 * @param boardState
	 * @return
	 */
	private Move minimax(BoardState boardState, Token playerType, int depth) {
		Move bestMove = new Move();
		int bestScore = (playerType == AIType) ? -1 : 1;
		Move currentMove;
		Coordinate bestCoordinate = new Coordinate(-1, -1);
		List<Coordinate> legalMoves = legalMoves(boardState);
		if (boardState.enoughInARow(opposite(AIType)) || boardState.enoughInARow(AIType) || boardState.isFull()) {
			bestScore = evaluate(boardState, playerType);
		} else {
			for (Coordinate c : legalMoves) {
				//Make this move
				boardState.placePiece(playerType, c.x, c.y);
				/*if (depth == 0 && playerType == AIType && (c.x == 0 && c.y == 0)) {
					System.out.println("starting on not the first block");
				}*/
				//Computer maximizing
				if (playerType == AIType) {
					currentMove = minimax(boardState, opposite(AIType), depth + 1);
					if (currentMove.score > bestScore) {
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove = currentMove;
					}

					//Human minimizing
				} else {
					currentMove = minimax(boardState, AIType, depth + 1);
					if (currentMove.score < bestScore) {
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove = currentMove;
					}
				}
				//undo move
				boardState.placePiece(Token.EMPTY, c.x, c.y);
			}
		}
		return bestMove;
	}



	/**
	 * @return
	 */
	private int evaluate(BoardState boardState, Token playerType) {
		int score = 0;
		if (playerType != AIType && (boardState.enoughInARow(playerType) || boardState.enoughInARow(opposite(playerType)))) {
			System.out.println("evaluating " + playerType + " someone won.");
			System.out.println(playerType + " won: " + boardState.enoughInARow(playerType) + " " + opposite(playerType) + " won: "
					+ boardState.enoughInARow(opposite(playerType)));
		}
		if (boardState.enoughInARow(playerType)) {
			score = (playerType == AIType) ? 1 : -1;

		}
		if (boardState.enoughInARow(opposite(playerType))) {
			score = (playerType == AIType) ? -1 : 1;
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



	private List<Coordinate> legalMoves(BoardState boardState) {
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
