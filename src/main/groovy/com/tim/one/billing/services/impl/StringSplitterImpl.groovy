package com.tim.one.billing.services.impl

import org.springframework.stereotype.Service
import com.tim.one.billing.services.StringSplitter

@Service
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
