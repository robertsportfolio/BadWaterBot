package com.badwater.bot.helpers;

import org.pircbotx.hooks.events.MessageEvent;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class RSSReader {
	private HashMap<String, URL> urlMap = new HashMap<String, URL> ();
	private HashMap<String, HashMap<String, String>> aggregatedNews = new HashMap<String, HashMap<String, String>> ();


	public RSSReader() throws IOException {
		initSourceConfig ();
	}

	private void initSourceConfig() throws IOException {
		File file = new File ( "./DB/Configs/NewsSources/" );
		if ( !file.exists () ) {
			file.mkdirs ();
		}
	}

	public void loadSources() throws IOException {
		File file = new File ( "./DB/Configs/NewsSources/NewsSources.conf" );
		if ( !file.exists () ) {
			System.out.println ( "Error!  No Such File!" + file.getPath () );
		}
		else {
			try (BufferedReader in = new BufferedReader ( new FileReader ( file ) )) {
				String lineIn = "";
				String[] args = { "", "" };
				while ( ( lineIn = in.readLine () ) != null ) {
					if ( lineIn.contains ( "=" ) ) {
						args = lineIn.split ( "=" );

					}
					if ( !urlMap.containsKey ( args[0] ) ) {
						urlMap.put ( args[0], new URL ( args[1] ) );
					}
				}
			}
			aggregateSilently ();
		}
	}

	public void aggregateSilently() throws IOException {
		for ( String source : urlMap.keySet () ) {
			String title = "";
			String url = "";

			try (BufferedReader in = new BufferedReader (
				   new InputStreamReader ( urlMap.get ( source ).openStream () ) )) {
				String line;
				HashMap<String, String> temp = new HashMap<String, String> ();
				while ( ( line = in.readLine () ) != null ) {
					if ( ( line.contains ( "<title>" ) || ( line.contains ( "<link>" ) ) ) ) {
						if ( line.contains ( "<title>" ) ) {
							int firstTitlePos = line.indexOf ( "<title>" );
							title = line.substring ( firstTitlePos );
							title = title.replace ( "<title>", "" );
							int lastTitlePos = title.indexOf ( "</title>" );
							title = title.substring ( 0, lastTitlePos );
						}
						if ( line.contains ( "<link>" ) ) {
							int firstLinkPos = line.indexOf ( "<link>" );
							url = line.substring ( firstLinkPos );
							url = url.replace ( "<link>", "" );
							int lastLinkPos = url.indexOf ( "</link>" );
							url = url.substring ( 0, lastLinkPos );
						}
						if ( !temp.containsKey ( title ) && title != null && url != null ) {
							temp.put ( title, url );
						}

					}
				}
				if ( !aggregatedNews.containsKey ( source ) ) {
					aggregatedNews.put ( source, temp );
				}
			}

		}
	}

	public void addSource(MessageEvent e, String name, String url) throws IOException {
		try {
			if ( e != null ) {
				if ( !urlMap.containsKey ( name.toLowerCase () ) ) {

					urlMap.put ( name, new URL ( url ) );
					e.getChannel ()
					 .send ()
					 .message ( e.getUser ().getNick () + ": Your News Source: " + name
					            + " Successfully Added With URL: " + url + " It will be updated once an hour" );

				}
				else {
					e.getChannel ()
					 .send ()
					 .message ( "Sorry " + e.getUser ().getNick () + " That Source Already Exists" );
				}
			}
			else {
				// just add the source
				if ( !urlMap.containsKey ( name.toLowerCase () ) ) {

					urlMap.put ( name, new URL ( url ) );

				}
			}

		} catch (MalformedURLException mue) {
			if ( e != null ) {
				e.getChannel ().send ().message ( e.getUser ().getNick () + "Sorry, that is not a valid URL" );
			}
			else {
				mue.printStackTrace ();
			}
		}
		//news source aggregating.
		aggregateSilently ();
	}

	public void showSources(MessageEvent e) {
		e.getUser ().send ().notice ( "SOURCES: " );
		for ( String tmp1 : urlMap.keySet () ) {
			e.getUser ().send ().notice ( tmp1 );
		}
	}

	public void update(MessageEvent e) throws IOException {
		e.getChannel ().send ().message ( "Okay " + e.getUser ().getNick () + "I'm aggregating news now." );
		saveSources ();
		aggregate ( e );

	}

	public void saveSources() throws IOException {
		File file = new File ( "./DB/Configs/NewsSources/" );
		if ( !file.exists () ) {
			file.mkdir ();
		}
		else {
			file = new File ( "./DB/Configs/NewsSources/NewsSources.conf" );
			try (BufferedWriter out = new BufferedWriter ( new FileWriter ( file ) )) {
				for ( String key : urlMap.keySet () ) {
					String lineOut = key + "=" + urlMap.get ( key ) + "\n";
					out.write ( lineOut );
				}
			}
		}
	}

	public void aggregate(MessageEvent e) throws IOException {
		e.getUser ().send ().notice ( "Aggregating" );
		aggregateSilently ();
		e.getUser ().send ().notice ( "AGGREGATION: COMPLETE!" );
	}

	public void showNews(MessageEvent e, String[] requestedSites) {
		for ( String source : requestedSites ) {

			if ( !aggregatedNews.containsKey ( source ) ) {
				e.getUser ()
				 .send ()
				 .message ( "Sorry " + e.getUser ().getNick () + ": I am not familiar with: " + source );
				e.getUser ().send ().message ( "You can add it with ?news add <source> <sourceURL>" );
			}
			else {
				String srcString = source;
				String divideMsg = "";
				int lengthifier = srcString.length () * 3;
				for ( int i = 0; i < lengthifier; i++ ) {
					divideMsg += "=";
					if ( i < ( lengthifier / 2 - srcString.length () / 2 ) ) {
						srcString = " " + srcString;
					}
					else if ( i > ( lengthifier / 2 ) + ( srcString.length () / 2 ) ) {
						srcString += " ";
					}
				}
				e.getUser ().send ().message ( divideMsg );
				e.getUser ().send ().message ( "||" + srcString + "||" );
				e.getUser ().send ().message ( divideMsg );
				String sendMessage = "";

				for ( int i = 0; i < 5; i++ ) {
					Object[] crunchifyKeys = aggregatedNews.get ( source ).keySet ().toArray ();

					String key = (String) crunchifyKeys[new Random ().nextInt ( crunchifyKeys.length )];
					sendMessage = key + " : " + aggregatedNews.get ( source ).get ( key );
					e.getUser ().send ().message ( sendMessage );
					aggregatedNews.get ( source ).remove ( key );
				}
			}
		}
	}

}
