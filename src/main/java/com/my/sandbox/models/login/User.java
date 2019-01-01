package com.my.sandbox.models.login;

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
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", isValid=" + isValid + "]";
	}
	
	@Override
	public User clone() {
		try {
			return (User)super.clone();
		} catch (CloneNotSupportedException ex) {
			throw new InternalError();
		}
	}
}
