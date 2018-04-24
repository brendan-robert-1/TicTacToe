/**
 *
 */
package com.brobert;

import com.beust.jcommander.JCommander;

/**
 * @author brobert
 *
 */
public class TTTGameRunner {
	public static void main(String[] argsv) {
		Args args = new Args();
		JCommander.newBuilder().addObject(args).build().parse(argsv);
		Game game = new TicTacToeGame(args);
		game.start();
	}

}
