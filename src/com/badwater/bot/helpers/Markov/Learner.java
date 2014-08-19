package com.badwater.bot.helpers.Markov;

import com.badwater.bot.helpers.helperFuncs;

import java.io.*;

/**
 * Created by irinix on 8/19/14.
 */
public class Learner {
	private static final String PATH_TO_FILE = "./DB/learner/learnedChains.chains";
	MarkovChain mC = new MarkovChain ();

	public Learner() throws IOException, ClassNotFoundException {
		/*if ( initLearnerFiles () ) {
			return;
		}
		System.out.println ( "Error Loading Learner Files!" );
	*/
	}

	public void learn(String msg) throws IOException, ClassNotFoundException {
		String[] msgArr = helperFuncs.prepMsgForLearner ( msg );
		mC.genChain ( msgArr );
		saveChains ( mC );
	}

	private void saveChains(MarkovChain mC) throws IOException, ClassNotFoundException {

		File file = new File ( PATH_TO_FILE );
		if ( file.exists () ) {
			file.delete ();
		}
		FileOutputStream fos = new FileOutputStream ( PATH_TO_FILE );
		ObjectOutputStream oos = new ObjectOutputStream ( fos );
		oos.writeObject ( mC );
		oos.writeObject ( null );
		oos.close ();
	}

	private boolean initLearnerFiles() throws IOException, ClassNotFoundException {
		boolean success = false;
		File file = new File ( "./DB/learner/" );
		if ( !file.exists () ) {
			file.mkdir ();
		}
		else {
			file = new File ( PATH_TO_FILE );
			if ( file.createNewFile () ) {
				System.out.println ( "Created File: " + file.getCanonicalPath () );
				success = true;
			}
			else {
				System.out.println ( "File: " + file.getCanonicalPath () + " Already Exists, Loading!" );
				loadChains ();
				file.delete ();
				success = true;
			}
		}
		return success;
	}

	private void loadChains() throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream ( PATH_TO_FILE );
		ObjectInputStream ois = new ObjectInputStream ( fis );
		while ( ois.readObject () != null ) {
			this.mC = (MarkovChain) ois.readObject ();
		}
		ois.close ();
	}
}
