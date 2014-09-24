
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

;

@Generated("org.jsonschema2pojo")
public class Queries {

	@Expose
	private List<NextPage> nextPage = new ArrayList<NextPage>();
	@Expose
	private List<Request>  request  = new ArrayList<Request>();

	public List<NextPage> getNextPage() {
		return nextPage;
	}

	public void setNextPage(List<NextPage> nextPage) {
		this.nextPage = nextPage;
	}

	public List<Request> getRequest() {
		return request;
	}

	public void setRequest(List<Request> request) {
		this.request = request;
	}

}
