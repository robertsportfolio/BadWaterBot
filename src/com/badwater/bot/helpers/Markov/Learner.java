package com.badwater.bot.helpers.Markov;

import com.badwater.bot.helpers.helperFuncs;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by irinix on 8/19/14.
 */
public class Learner {
	private static final String PATH_TO_FILE = "./DB/learner/Markov.chains";
	private final MarkovChain mC = new MarkovChain();
	private final Thread gblThread;
	private ArrayList<String> mcQueue = new ArrayList<String>();
	private GutenbergReader gutenbergReader;
	private ArrayList<Thread> threadPool = new ArrayList<Thread>();

	public Learner() throws IOException, ClassNotFoundException {
		initLearnerFiles ();
		mC.printChains ();
		gutenbergReader = new GutenbergReader(this);
		gblThread = new Thread(gutenbergReader);
		gblThread.start();
	}

	private void initLearnerFiles() throws IOException, ClassNotFoundException {
		//check to make sure the file directory exists.  If not, create it.
		File file = new File ( "./DB/learner/" );
		if ( !file.exists () ) {
			file.mkdirs ();
		}
		//if the directory DOES exist, check to see if the Markov.Chains File exists.
		else {
			file = new File ( PATH_TO_FILE );
			if ( file.exists () ) {
				System.out.println ( "File: " + file.getCanonicalPath () + " Already Exists, Loading!" );
				loadChains ();
			}
			else {
				System.out.println (
					   "File: " + PATH_TO_FILE + " Does Not Exist.  It Will Be Automagically Created");
			}
		}
	}


	private void loadChains() throws IOException, ClassNotFoundException {

		//Init an ObjectInputStream to load mC from file.
		try (FileInputStream fis = new FileInputStream ( PATH_TO_FILE );
		     ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis))) {
			mC.setChains((HashMap<String, HashMap<String, Integer>>) ois.readObject());
		}
	}


	public void addToAndProcessQueue(String s) throws InterruptedException, IOException, ClassNotFoundException {
		mcQueue.add(s);
		if (mcQueue.size() >= 100) {
			while (mcQueue.size() > 0) {
				processQueue();
			}
			saveChains();
		}
		else {
			int x = 100 - mcQueue.size();
			System.out.println("Waiting for " + x + " More lines");
		}
		boolean somethingIsReading = false;
		for (Thread t : threadPool) {
			if (t.isAlive()) {
				somethingIsReading = true;
			}

		}
		if (!somethingIsReading) {
			processQueue();
		}
	}


	private void processQueue() {
		for (String s : mcQueue) {
			System.out.println("Sending Line \"" + s + "\" to mC.genChain(" + s + ")");
			mC.genChain(helperFuncs.prepMsgForLearner(s));

		}
		mcQueue.clear();
	}

	private void saveChains() throws IOException, EOFException, InterruptedException {

		System.out.println("\n\n{{{{{{{{{{{{{{{SAVING CHAINS}}}}}}}}}}}}}}}}}}}\n\n");

		//okay, file's been emptied.  Save our chains to file.
		try (FileOutputStream fos = new FileOutputStream ( PATH_TO_FILE );
		     ObjectOutputStream oos = new ObjectOutputStream ( fos )) {
			oos.writeObject(mC.getChain());
		}
		System.out.println("\n\n{{{{{{{{{{{{{{{CHAINS SAVED}}}}}}}}}}}}}}}}}}}\n\n");

	}
}
