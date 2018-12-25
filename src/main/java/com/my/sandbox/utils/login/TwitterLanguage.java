package com.my.sandbox.utils.login;

public enum TwitterLanguage {
	ENGLISH("English");

	private final String language;

	private TwitterLanguage(String language) {
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}
}
