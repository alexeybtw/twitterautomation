package com.my.sandbox.models.tweet;

import com.my.sandbox.models.BaseModel;
import com.poiji.annotation.ExcelCellName;

public class Tweet extends BaseModel {

	@ExcelCellName("TweetText")
	private String tweetText;

	@ExcelCellName("IsValid")
	private boolean isValid;

	public String getTweetText() {
		return tweetText;
	}

	public boolean isValid() {
		return isValid;
	}

	public int getTweetLength() {
		return this.tweetText.length();
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetText=" + tweetText + ", isValid=" + isValid + "]";
	}
}
