
package com.badwater.bot.helpers.Readers.GoogleSearch.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

;

@Generated("org.jsonschema2pojo")
public class Pagemap {

	@SerializedName("cse_image")
	@Expose
	private List<CseImage>     cseImage     = new ArrayList<CseImage>();
	@Expose
	private List<Videoobject>  videoobject  = new ArrayList<Videoobject>();
	@Expose
	private List<Person>       person       = new ArrayList<Person>();
	@SerializedName("cse_thumbnail")
	@Expose
	private List<CseThumbnail> cseThumbnail = new ArrayList<CseThumbnail>();
	@Expose
	private List<Metatag>      metatags     = new ArrayList<Metatag>();
	@Expose
	private List<Imageobject>  imageobject  = new ArrayList<Imageobject>();

	public List<CseImage> getCseImage() {
		return cseImage;
	}

	public void setCseImage(List<CseImage> cseImage) {
		this.cseImage = cseImage;
	}

	public List<Videoobject> getVideoobject() {
		return videoobject;
	}

	public void setVideoobject(List<Videoobject> videoobject) {
		this.videoobject = videoobject;
	}

	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}

	public List<CseThumbnail> getCseThumbnail() {
		return cseThumbnail;
	}

	public void setCseThumbnail(List<CseThumbnail> cseThumbnail) {
		this.cseThumbnail = cseThumbnail;
	}

	public List<Metatag> getMetatags() {
		return metatags;
	}

	public void setMetatags(List<Metatag> metatags) {
		this.metatags = metatags;
	}

	public List<Imageobject> getImageobject() {
		return imageobject;
	}

	public void setImageobject(List<Imageobject> imageobject) {
		this.imageobject = imageobject;
	}

}
