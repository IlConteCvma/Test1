package logic.model;

import logic.Session;

public class StringParser {

	public String parseStringMaps() {
		Student studLog = Session.getSession().getStudent();
		String address = studLog.getAddress().getStreet();
		StringBuilder bld = new StringBuilder();
		bld.append("https://google.com/maps/dir/");
		for(int i=0;i<address.length();i++) {
			if(address.charAt(i) == ' ') {
				bld.append('+');
			}else {
				bld.append(address.charAt(i));
			}
		}
		
		bld.append(",+" + studLog.getAddress().getStreetNumber() + ",+" + studLog.getAddress().getCity() + "/Via+del+Politecnico,+1,+00100+Roma+RM");
		
		
		return bld.toString();
	}
	
}
