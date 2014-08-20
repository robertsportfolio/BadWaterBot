package com.badwater.bot.helpers.Markov;

import com.badwater.bot.helpers.helperFuncs;

import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by irinix on 8/19/14.
 */
public class Learner {
	private static final String PATH_TO_FILE = "./DB/learner/Markov.chains";
	private GutenbergLearner gbl;
	private MarkovChain mC = new MarkovChain ();
	private ArrayBlockingQueue<String> blQueue = new ArrayBlockingQueue<String> ( 25 );

	public Learner() throws IOException, ClassNotFoundException {
		initLearnerFiles ();
		mC.printChains ();
		gbl = new GutenbergLearner ( this );
		( new Thread ( gbl ) ).start ();

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
					   "File: " + PATH_TO_FILE + " Does Not Exist.  It Will Be Automagicaly Created" );
			}
		}
	}


	private void loadChains() throws IOException, ClassNotFoundException {

		//Init an ObjectInputStream to load mC from file.
		try (FileInputStream fis = new FileInputStream ( PATH_TO_FILE );
		     ObjectInputStream ois = new ObjectInputStream ( new BufferedInputStream ( fis ) )) {
			mC = (MarkovChain) ois.readObject ();
		}
	}


	public void learn(String msg) throws IOException, ClassNotFoundException, ClassCastException {
		//send message to mC for chain generation.
		blQueue.offer ( msg );
		while ( !blQueue.isEmpty () ) {
			System.out.println ( "=====\nSending Line: " + blQueue.peek () + " to mC.genChain()\n=====" );
			mC.genChain ( helperFuncs.prepMsgForLearner ( blQueue.poll () ) );
		}
		saveChains ();
	}


	private void saveChains() throws IOException, ClassNotFoundException {


		//okay, file's been emptied.  Save our chains to file.
		try (FileOutputStream fos = new FileOutputStream ( PATH_TO_FILE );
		     ObjectOutputStream oos = new ObjectOutputStream ( fos )) {
			oos.writeObject ( mC );
		}
	}
}
