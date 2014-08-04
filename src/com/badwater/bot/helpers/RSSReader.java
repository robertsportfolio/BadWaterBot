package com.badwater.bot.helpers;

import org.pircbotx.hooks.events.MessageEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class RSSReader {
	private HashMap<String, URL> urlMap = new HashMap<String, URL> ();
	private HashMap<String, HashMap<String, String>> aggregatedNews = new HashMap<String, HashMap<String, String>> ();
	private Tuple Retval;

	public RSSReader() {
		addSource ( null, "cnn", "http://rss.cnn.com/rss/edition.rss" );
		addSource ( null, "techdirt", "http://feeds.feedburner.com/techdirt/feed" );
		addSource ( null, "espn", "http://sports.espn.go.com/espn/rss/news?format=xml" );

	}

	public void addSource(MessageEvent e, String name, String url) {
		try {
			if ( e != null ) {
				if ( !urlMap.containsKey ( name.toLowerCase () ) ) {

					urlMap.put ( name, new URL ( url ) );
					e.getChannel ()
					 .send ()
					 .message ( e.getUser ().getNick () + ": Your News Source: " + name
					            + " Successfully Added With URL: " + url + " It will be updated once an hour" );
					e.getChannel ().send ().message ( "SOURCES: " );
					for ( String tmp1 : urlMap.keySet () ) {
						e.getChannel ().send ().message ( tmp1 );
					}
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

	public void aggregateSilently() {
		for ( String source : urlMap.keySet () ) {
			String title = "";
			String url = "";
			try {
				BufferedReader in =
					   new BufferedReader ( new InputStreamReader ( urlMap.get ( source ).openStream () ) );
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
				in.close ();
				if ( !aggregatedNews.containsKey ( source ) ) {
					aggregatedNews.put ( source, temp );
				}
			} catch (IOException e1) {
				
				e1.printStackTrace ();
			}

		}
	}

	public void update(MessageEvent e) {
		e.getChannel ().send ().message ( "Okay " + e.getUser ().getNick () + "I'm aggregating news now." );
		aggregate ( e );

	}

	public void aggregate(MessageEvent e) {
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
				e.getUser ().send ().message ( source );
				e.getUser ().send ().message ( "========================" );
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
