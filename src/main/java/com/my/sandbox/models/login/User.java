package com.my.sandbox.models.login;

import java.util.List;

import com.poiji.annotation.ExcelCellName;

public class User {

	@ExcelCellName("Username")
	private String username;

	@ExcelCellName("Password")
	private String password;

	@ExcelCellName("IsValid")
	private boolean isValid;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isValid() {
		return isValid;
	}

	public static User getUserByValidity(List<User> lstUsers, boolean isValid) {
		for (User user : lstUsers)
			if (user.isValid() == isValid)
				return user;

		return null;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isValid=" + isValid + "]";
	}
}
