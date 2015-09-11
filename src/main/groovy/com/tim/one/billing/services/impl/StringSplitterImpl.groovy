package com.tim.one.billing.services.impl

import com.tim.one.billing.services.StringSplitter

class StringSplitterImpl implements StringSplitter {
	
	public List<String> split(String string) {
		StringTokenizer token = new StringTokenizer(string, ",")
			List<String> list = new ArrayList<String>(string.length())
			while(token.hasMoreTokens()){
				 list.add(token.nextToken())
			 }
		return list
	}

}
