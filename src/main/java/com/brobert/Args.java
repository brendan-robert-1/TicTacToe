/**
 *
 */
package com.brobert;

import com.beust.jcommander.Parameter;
import com.brobert.Board.Token;

/**
 * @author brobert
 *
 */
public class Args {

	@Parameter(names = { "--huamn", "-h" }, required = false)
	private Token human = Token.X;

	@Parameter(names = { "-w", "--width" }, required = false)
	private int width = 3;

	@Parameter(names = { "-f", "--first" }, required = false)
	private String first = "computer";



	public String getFirst() {
		if (!(first.equalsIgnoreCase("computer") || first.equalsIgnoreCase("human"))) {
			System.out.println("-f and --first must have either computer or human passed in as an argument for who should go first.");
			System.exit(1);
		}
		return first;
	}



	public Token getHuman() {
		return human;
	}



	/**
	 * @return
	 */
	public int getWidth() {
		return width;
	}
}
