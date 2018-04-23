/**
 *
 */
package com.brobert;

/**
 * @author brobert
 *
 */
public class Board {

	enum Token {
		X, O, EMPTY
	}

	private int width;

	private Token[][] squares;



	public void placePiece(Token type, int x, int y) {
		squares[y][x] = type;
	}



	public Board(int width) {
		if (width < 3) {
			throw new IllegalArgumentException("width of playign board must be greater than 2");
		} else {
			this.width = width;
			squares = new Token[width][width];
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < width; j++) {
					squares[i][j] = Token.EMPTY;
				}
			}
		}
	}



	public void printBoard() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (squares[i][j] == Token.X) {
					System.out.print(" X ");
				} else if (squares[i][j] == Token.O) {
					System.out.print(" O ");
				} else {
					System.out.print("   ");
				}
				if (j != width - 1) {
					System.out.print("|");
				}
			}
			if (i != width - 1) {
				System.out.print("\n");
				for (int w = 0; w < width; w++) {
					System.out.print("----");
				}
				System.out.print("\n");
			}
		}
		System.out.print("\n");
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (squares[i][j] == Token.X) {
					sb.append(" X ");
				} else if (squares[i][j] == Token.O) {
					sb.append(" O ");
				} else {
					sb.append("   ");
				}
				if (j != width - 1) {
					sb.append("|");
				}
			}
			if (i != width - 1) {
				sb.append("\n");
				for (int w = 0; w < width; w++) {
					sb.append("----");
				}
				sb.append("\n");
			}
		}
		sb.append("\n");
		return sb.toString();
	}



	/**
	 * @param boardState
	 * @return
	 */
	public static Board copyOf(Board boardState) {
		Board copy = new Board(boardState.width);
		for (int i = 0; i < boardState.width; i++) {
			for (int j = 0; j < boardState.width; j++) {
				copy.squares[i][j] = boardState.squares[i][j];
			}
		}
		return copy;
	}



	public Token at(int x, int y) {
		return squares[y][x];
	}



	public int getWidth() {
		return width;
	}



	/**
	 * @return
	 */
	public boolean isFull() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (squares[i][j] == Token.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}



	/**
	 * @param computer2
	 * @return
	 */
	public boolean enoughInARow(Token square) {

		boolean horizontal = horizontalCheck(square);
		boolean vertical = verticalCheck(square);
		boolean diagonal = diagnoalCheck(square);
		return horizontal || vertical || diagonal;
	}



	/**
	 * @param square
	 */
	private boolean diagnoalCheck(Token square) {
		boolean down = down(square);
		boolean up = up(square);
		return down || up;
	}



	/**
	 * @param square
	 * @return
	 */
	private boolean up(Token square) {
		boolean upWin = true;
		int x = 0, y = width - 1;
		for (int i = width; i > 0; i--) {
			Token whatsThere = at(x, y);
			if (whatsThere != square) {
				upWin = false;
				break;
			}
			x++;
			y--;
		}
		return upWin;
	}



	/**
	 * @param square
	 * @return
	 */
	private boolean down(Token square) {
		boolean downWin = true;
		for (int i = 0; i < width; i++) {
			if (at(i, i) != square) {
				downWin = false;
				break;
			}
		}
		return downWin;
	}



	/**
	 * @param square
	 */
	private boolean verticalCheck(Token square) {
		for (int i = 0; i < width; i++) {
			boolean verticalWin = true;
			for (int j = 0; j < width; j++) {
				if (at(j, i) != square) {
					verticalWin = false;
					break;
				}
			}
			if (verticalWin) {
				return true;
			}
		}
		return false;
	}



	/**
	 * @param square
	 */
	private boolean horizontalCheck(Token square) {
		for (int i = 0; i < width; i++) {
			boolean hoirzontal = true;
			for (int j = 0; j < width; j++) {
				if (at(i, j) != square) {
					hoirzontal = false;
					break;
				}
			}
			if (hoirzontal) {
				return true;
			}
		}
		return false;
	}
}
