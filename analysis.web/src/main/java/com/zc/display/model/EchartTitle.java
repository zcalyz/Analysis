package com.zc.display.model;

/**
 * echart 标题类
 * @author zhaichen
 *
 */
public class EchartTitle {
	
	/**
	 * 表格标题
	 */
	private String text;
	
	/**
	 * 表格子标题
	 */
	private String subtext;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubtext() {
		return subtext;
	}

	public void setSubtext(String subtext) {
		this.subtext = subtext;
	}
	
}
