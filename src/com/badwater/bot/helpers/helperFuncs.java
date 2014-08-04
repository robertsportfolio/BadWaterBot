package com.badwater.bot.helpers;

/**
 * Created by irinix on 8/3/14.
 */
public class helperFuncs {
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

	public static int convertToInt(String s) {
		int retVal = -1;
		if ( s.matches ( "^10 | [0-9]$" ) ) {
			System.out.println ( "In Convert to int" + s );
			retVal = Integer.parseInt ( s );
		}
		return retVal;
	}

}
