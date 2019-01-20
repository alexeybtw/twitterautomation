package com.my.sandbox.models.login;

import java.util.ArrayList;
import java.util.List;

import com.my.sandbox.models.BaseModel;
import com.poiji.annotation.ExcelCellName;

public class User extends BaseModel {

	@ExcelCellName("Username")
	private String username;

	@ExcelCellName("Password")
	private String password;

	@ExcelCellName("IsValid")
	private boolean isValid;

	@ExcelCellName("Description")
	private String description;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isValid() {
		return isValid;
	}

	public String getDescription() {
		return description;
	}

	public static List<User> getUsersByValidity(List<User> lstUsers, boolean isValid) {
		List<User> lstFilteredUsers = new ArrayList<User>();

		for (User user : lstUsers)
			if (user.isValid() == isValid)
				lstFilteredUsers.add(user);

		return lstFilteredUsers;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", isValid=" + isValid + ", description="
				+ description + "]";
	}
}
