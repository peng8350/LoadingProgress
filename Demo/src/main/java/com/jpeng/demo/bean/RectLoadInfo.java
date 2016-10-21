package com.jpeng.demo.bean;

import android.graphics.Bitmap;

/**
 * Created by peng on 16-10-19.
 */
public class RectLoadInfo {

	private String	name;
	private String	intro;
	private String	url;

	private int		position;

	private Bitmap	progress_image;

	private int		bottomColor;
	private int		progressColor;

	public RectLoadInfo(String name, String intro, String url) {
		this.name = name;
		this.intro = intro;
		this.url = url;
	}

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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Bitmap getProgress_image() {
		return progress_image;
	}

	public void setProgress_image(Bitmap progress_image) {
		this.progress_image = progress_image;
	}

	public int getBottomColor() {
		return bottomColor;
	}

	public void setBottomColor(int bottomColor) {
		this.bottomColor = bottomColor;
	}

	public int getProgressColor() {
		return progressColor;
	}

	public void setProgressColor(int progressColor) {
		this.progressColor = progressColor;
	}
}
