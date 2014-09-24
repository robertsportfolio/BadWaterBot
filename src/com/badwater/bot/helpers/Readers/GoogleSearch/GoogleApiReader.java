package com.badwater.bot.helpers.Readers.GoogleSearch;

import com.badwater.bot.helpers.Readers.GoogleSearch.Results.GoogleResults;
import com.badwater.bot.helpers.Readers.GoogleSearch.Results.Item;
import com.badwater.bot.helpers.Readers.bwFileReader;
import com.badwater.bot.helpers.helperFuncs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by irinix on 9/23/14.
 */
public class GoogleApiReader {
	GoogleResults results;
	ArrayList<String> items = new ArrayList<String>();
	private String charSet = "UTF-8";

	public GoogleApiReader(String searchString) throws IOException {
		String googleURL = "https://www.googleapis.com/customsearch/v1?key=";
		String googleAPIKey = new bwFileReader("./DB/apikeys/apikeys").getAPIKey("google");

		if (!googleAPIKey.equals(null)) {
			googleURL = googleURL + googleAPIKey + "&cx=003185079202888913861:ztjjpl__duy&q=" + helperFuncs.parseForSearch(
				   searchString + "&alt=json");
			System.out.println(googleURL);
			URL searchURL = new URL(googleURL);
			HttpURLConnection urlConn = (HttpURLConnection) searchURL.openConnection();
			Gson googleReply = new GsonBuilder().create();
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String lineIn = "";
			String json = "";
			while ((lineIn = reader.readLine()) != null) {

				json += lineIn;
			}
			results = googleReply.fromJson(json, GoogleResults.class);
			/*System.out.println(results.getContext());
			System.out.println(results.getQueries());
			System.out.println(results.getUrl());
			System.out.print(results.getKind());*/
			for (Item i : results.getItems()) {
				items.add(i.getTitle() + " :: " + i.getLink());
			}

		}
		else {
			System.out.println("ERROR!  Results are NULL!");
			results = null;
		}
	}

	public ArrayList<String> getResults() {
		return items;
	}

	public GoogleResults getResults1() {
		return results;
	}
}

