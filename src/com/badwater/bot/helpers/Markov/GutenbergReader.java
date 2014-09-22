package com.badwater.bot.helpers.Markov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by irinix on 8/20/14.
 */


public class GutenbergReader implements Runnable {
	final   Learner learner;
	private URL     url;
	private boolean threadSuspended;

	public GutenbergReader(Learner l) {
		learner = l;
	}

	@Override
	public void run() {
		synchronized (this) {
			while (threadSuspended) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		try (BufferedReader in = new BufferedReader(
			   new InputStreamReader(new URL("http://www.gutenberg.org/files/92/92.txt").openStream()))) {
			String line = "";

			while ((line = in.readLine()) != null) {
				if (!line.equals("")) {
					System.out.println("Sending Line: \"" + line + "\" To Learner.addToQueue()");
					learner.addToAndProcessQueue((line));
				}
				else {
					continue;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	private void connectToText() {

		try {
			url = new URL("http://www.gutenberg.org/files/90/90.txt");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
