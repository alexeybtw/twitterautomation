package com.my.sandbox.utils.login;

import com.my.sandbox.models.login.User;

public class LoggedUser {

	private static User loggedUser = null;

	private LoggedUser() {
	}

	public static void setLoggedUser(User user) {
		if (loggedUser == null && user instanceof User)
			loggedUser = user.clone();
	}

	public static User getLoggedUser() {
		return loggedUser;
	}

	public static void disposeLoggedUser() {
		loggedUser = null;
	}
}
