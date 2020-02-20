package logic.controller;

import logic.Session;
import logic.model.Address;
import logic.model.StringParser;

public class ViewRoadController {
	
	public String getRoad() {
		Address addrStudent = Session.getSession().getStudent().getAddress();
		StringParser sParse = new StringParser();
		return sParse.parseStringMaps(addrStudent);
	}
	
}
