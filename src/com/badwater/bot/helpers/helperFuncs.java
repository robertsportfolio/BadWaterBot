package com.badwater.bot.helpers;

import java.util.Random;

/**
 * Created by irinix on 8/3/14.
 */
public class helperFuncs {
	private static Random random = new Random ();

	public static int nextIntInRange(int max, int min) {
		int retVal = random.nextInt ( ( max - min ) + 1 ) - min;
		return retVal;
	}

	public static String[] toArgs(String msg) {

		for ( String s : msg.split ( " " ) ) {

		}
		return msg.split ( " " );
	}

	public static boolean isInt(String s) {
		boolean isTni = false;
		if ( s.matches ( "^10|[0-9]$" ) ) {
			//System.out.println(s);
			isTni = true;
		}

		return isTni;
	}

	public static boolean boundsCheck(char[][] arr, int y, int x) {
		if ( x == 0 || y == 0 || x + 1 >= arr[y].length || y + 1 >= arr.length ) {
			return true;

		}
		else {
			return false;
		}
	}

	public static int convertToInt(String s) {
		int retVal = -1;
		if ( s.matches ( "^10 | [0-9]$" ) ) {
			System.out.println ( "In Convert to int" + s );
			retVal = Integer.parseInt ( s );
		}
		return retVal;
	}

}
