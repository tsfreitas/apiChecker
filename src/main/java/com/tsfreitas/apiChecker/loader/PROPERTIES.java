package com.tsfreitas.apiChecker.loader;

public enum PROPERTIES {
	API_TAG("api.tag", "0.0.0"), API_ALERT("api.alert", "true"), API_EMAIL("api.email",
			"false"), GITHUB_TAG("github.tag", "0.0.0"), GITHUB_ALERT("github.alert",
					"true"), GITHUB_EMAIL("github.email", "false");

	private String propertyName;

	private String defaultValue;

	private PROPERTIES(String propertyName, String defaultValue) {
		this.propertyName = propertyName;
		this.defaultValue = defaultValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

}
