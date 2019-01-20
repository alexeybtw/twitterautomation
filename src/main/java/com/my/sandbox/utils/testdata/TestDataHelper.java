package com.my.sandbox.utils.testdata;

import java.util.List;

import com.my.sandbox.models.BaseModel;

public class TestDataHelper {

	public static <TModel extends BaseModel> Object[][] covertObjListToArray(List<TModel> lstModels) {
		Object[][] arrTestData = lstModels.size() == 0 ? new Object[][] {} : new Object[lstModels.size()][];

		for (int i = 0; i < arrTestData.length; i++)
			arrTestData[i] = new Object[] { lstModels.get(i).cloneModel() };

		return arrTestData;
	}
}
