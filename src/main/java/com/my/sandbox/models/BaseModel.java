package com.my.sandbox.models;

import java.util.List;

import com.poiji.annotation.ExcelCellName;

public class BaseModel implements Cloneable {

	@ExcelCellName("Id")
	protected long id;

	public static <T extends BaseModel> T getObjectById(List<T> lstObjects, long id) {
		for (T object : lstObjects) {
			if (object.getId() == id)
				return object;
		}

		return null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
