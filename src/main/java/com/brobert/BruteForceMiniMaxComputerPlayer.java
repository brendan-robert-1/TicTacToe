package com.brobert;

import java.util.ArrayList;
import java.util.List;

import com.brobert.TwoDimensionalBoard.Token;

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

	int minimaxCalls = 0;



	@Override
	public Coordinate play(TwoDimensionalBoard boardState) {
		List<Coordinate> legalMoves = legalMoves(boardState);
		Move bestMove = minimaxAlphaBeta(boardState, AIType, 0, -1, 1);
		System.out.println("minimax total calls: " + minimaxCalls);
		return bestMove.coord;
	}

	/*
	
		*//**
			 * Keeping this commented out mainly for reference as a non-alpha
			 * beta pruned version of minimax.
			 *
			 * @param board
			 * @return
			 *//*
				private Move minimax(Board board, Token playerType, int depth) {
				minimaxCalls++;
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
				
				*/



	/**
	 * @param board
	 * @return
	 */
	private Move minimaxAlphaBeta(TwoDimensionalBoard board, Token playerType, int depth, int alpha, int beta) {
		minimaxCalls++;
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
				board.placePiece(c.x, c.y, playerType);

				//Computer maximizing
				if (playerType == AIType) {
					currentMove = minimaxAlphaBeta(board, opposite(AIType), depth + 1, alpha, beta);
					if (currentMove.score > alpha) {
						alpha = currentMove.score;
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;
					}

					//Human minimizing
				} else {
					currentMove = minimaxAlphaBeta(board, AIType, depth + 1, alpha, beta);
					if (currentMove.score < beta) {
						beta = currentMove.score;
						bestScore = currentMove.score;
						bestMove.coord = c;
						bestMove.score = bestScore;
					}
				}
				//undo move
				board.placePiece(c.x, c.y, Token.EMPTY);
				if (depth == 0) {
					System.out.println(c + " " + alpha);
				}
				if (alpha >= beta) {
					break;
				}
			}
		}
		return bestMove;
	}



	/**
	 * @return
	 */
	private int evaluate(TwoDimensionalBoard board) {
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



	private List<Coordinate> legalMoves(TwoDimensionalBoard boardState) {
		List<Coordinate> legalMoves = new ArrayList<>();
		for (int i = 0; i < boardState.width(); i++) {
			for (int j = 0; j < boardState.width(); j++) {
				if (boardState.at(j, i) == Token.EMPTY) {
					legalMoves.add(new Coordinate(j, i));
				}
			}
		}
		return legalMoves;
	}
}
