/**
 *
 */
package com.brobert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.brobert.BoardState.Token;

/**
 * @author brobert
 *
 */
public class RandomComputerPlayer implements ComputerPlayer {

	private Token playerType;



	public RandomComputerPlayer(Token playerType) {
		this.playerType = playerType;
	}



	@Override
	public Coordinate play(BoardState boardState) {
		List<Coordinate> validPlaces = getValidCoordinates(boardState);
		int randomNum = ThreadLocalRandom.current().nextInt(0, validPlaces.size());
		return validPlaces.get(randomNum);

	}



	/**
	 * @param boardState
	 * @return
	 */
	private List<Coordinate> getValidCoordinates(BoardState boardState) {
		List<Coordinate> validCoords = new ArrayList<>();
		for (int i = 0; i < boardState.getWidth(); i++) {
			for (int j = 0; j < boardState.getWidth(); j++) {
				if (boardState.at(j, i) == Token.EMPTY) {
					validCoords.add(new Coordinate(j, i));
				}
			}
		}
		return validCoords;
	}

}
