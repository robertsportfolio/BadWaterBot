
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

;

@Generated("org.jsonschema2pojo")
public class Imageobject {

	@Expose
	private String url;
	@Expose
	private String width;
	@Expose
	private String height;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}


}
