package com.my.sandbox.utils;

import java.io.File;
import java.net.URL;

public class ResourceHelper {

	public static URL getResource(String resourcePath) {
		URL resource = null;

		try {
			resource = ResourceHelper.class.getResource(resourcePath);
		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
		return resource;
	}

	public static File getResourceFile(String resourcePath) {
		String filePath = getResource(resourcePath).getPath();
		return new File(filePath);
	}
}
