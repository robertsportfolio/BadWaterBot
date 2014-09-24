
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.annotation.Generated;

;

@Generated("org.jsonschema2pojo")
public class CseThumbnail {

	@Expose
	private String width;
	@Expose
	private String height;
	@Expose
	private String src;

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

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
