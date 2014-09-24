
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

;

@Generated("org.jsonschema2pojo")
public class SearchInformation {

	@Expose
	private Double searchTime;
	@Expose
	private String formattedSearchTime;
	@Expose
	private String totalResults;
	@Expose
	private String formattedTotalResults;

	public Double getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(Double searchTime) {
		this.searchTime = searchTime;
	}

	public String getFormattedSearchTime() {
		return formattedSearchTime;
	}

	public void setFormattedSearchTime(String formattedSearchTime) {
		this.formattedSearchTime = formattedSearchTime;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public String getFormattedTotalResults() {
		return formattedTotalResults;
	}

	public void setFormattedTotalResults(String formattedTotalResults) {
		this.formattedTotalResults = formattedTotalResults;
	}


}
