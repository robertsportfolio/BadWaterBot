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
		if ( initLearnerFiles () ) {
			System.out.println ( "Files Loaded Successfully" );
			return;
		}
		System.out.println ( "Error Loading Learner Files!" );

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
				//delete the file to simplify my life.  Since only one object is being written,
				// I don't need to loop
				//over things.
				file.delete ();
				success = true;
			}
		}
		return success;
	}

	private void loadChains() throws IOException, ClassNotFoundException {

		//Init an ObjectInputStream to load mC from file.
		FileInputStream fis = new FileInputStream ( PATH_TO_FILE );
		ObjectInputStream ois = new ObjectInputStream ( new BufferedInputStream ( fis ) );
		this.mC = (MarkovChain) ois.readObject ();
		//this is just here to debug whether it's loading or not.
		this.mC.printChains ();
		ois.close ();
	}


	public void learn(String msg) throws IOException, ClassNotFoundException {
		//send message to mC for chain generation.
		String[] msgArr = helperFuncs.prepMsgForLearner ( msg );
		mC.genChain ( msgArr );
		saveChains ( mC );
	}

	private void saveChains(MarkovChain mC) throws IOException, ClassNotFoundException {

		//Just make sure the file is empty, before we start.
		FileOutputStream clrFile = new FileOutputStream ( PATH_TO_FILE );
		clrFile.close ();
		//okay, file's been emptied.  Save our chains to file.
		FileOutputStream fos = new FileOutputStream ( PATH_TO_FILE );
		ObjectOutputStream oos = new ObjectOutputStream ( fos );
		oos.writeObject ( mC );
		//close that shit down.
		oos.close ();
	}
}
