package com.jpeng.demo.bean;

/**
 * Created by peng on 16-10-20.
 */
public class DifferentInfo {
	private String	name;
	private String	intro;
	private String	url;

	public DifferentInfo(String name, String intro, String url) {
		this.name = name;
		this.intro = intro;
		this.url = url;
	}

	private int type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
