package com.jpeng.demo.bean;

import com.jpeng.progress.enums.CircleStyle;

/**
 * Created by peng on 16-10-19.
 */
public class CircleLoadInfo {
	private String	name;
	private String	intro;
	private String	url;
	private int		textColor;
	private int		textSize;
	private int		bottomColor;
	private int		progressColor;
	private int		radius;
	private int		strokeWidth;

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
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

	public CircleLoadInfo(String name, String intro, String url) {
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

	public CircleStyle getStyle() {
		return style;
	}

	public void setStyle(CircleStyle style) {
		this.style = style;
	}

	public int[] getGradient() {
		return gradient;
	}

	public void setGradient(int[] gradient) {
		this.gradient = gradient;
	}

	private CircleStyle	style;
	private int[]		gradient;
}
