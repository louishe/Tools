package com.msky.tools.ui;

import java.util.ArrayList;
import java.util.List;

public class AAEntries {
	
	private String category;
	
	private List<String> entries = new ArrayList<String>();
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<String> getEntries() {
		return entries;
	}

	public void setEntries(List<String> entries) {
		this.entries = entries;
	}

}
