package com.msky.tools.util;

import java.util.ResourceBundle;

public class ToolsHelper {
	
	public static String getMessage(String key)
	{
		ResourceBundle bundler = ResourceBundle.getBundle("AATools");
		return bundler.getString(key);
	}

}
