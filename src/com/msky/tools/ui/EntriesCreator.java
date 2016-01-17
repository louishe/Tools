package com.msky.tools.ui;

import java.util.ArrayList;
import java.util.List;

public class EntriesCreator {
	
	public static List<AAEntries> createEntries()
	{
		List<AAEntries> entries = new ArrayList<AAEntries>();
		AAEntries dbcEntry = new AAEntries();
		dbcEntry.setCategory("DB Switch");
		List<String> dbcList = new ArrayList<String>();
		dbcList.add("DB Switch");
		entries.add(dbcEntry);
		return entries;
	}

}
