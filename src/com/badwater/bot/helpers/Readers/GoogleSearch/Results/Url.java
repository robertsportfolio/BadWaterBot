
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

;

@Generated("org.jsonschema2pojo")
public class Url {

	@Expose
	private String type;
	@Expose
	private String template;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}


}
