package com.badwater.bot.helpers.Markov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by irinix on 8/20/14.
 */


public class GutenbergLearner implements Runnable {
	Learner learner;
	URL url;

	public GutenbergLearner(Learner l) {
		learner = l;
	}

	@Override public void run() {
		try (BufferedReader in = new BufferedReader (
			   new InputStreamReader ( new URL ( "http://www.gutenberg.org/files/90/90.txt" ).openStream () ) )) {
			String line = "";
			while ( ( line = in.readLine () ) != null ) {
				System.out.println ( "Sending Line: " + line + " To Learner.learn()" );
				learner.learn ( ( line ) );
			}
		} catch (IOException e) {
			e.printStackTrace ();
		} catch (ClassNotFoundException e) {
			e.printStackTrace ();
		}
	}

	private void connectToText() {

		try {
			URL url = new URL ( "http://www.gutenberg.org/files/90/90.txt" );
			this.url = url;
		} catch (MalformedURLException e) {
			e.printStackTrace ();
		}

	}

}
