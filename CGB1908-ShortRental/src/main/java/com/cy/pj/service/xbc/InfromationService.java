package com.cy.pj.service.xbc;

import com.cy.pj.vo.xbc.PersonalInformation;

public interface InfromationService {
	
	void insert(PersonalInformation  e);
	public PersonalInformation selectPersonalInformation(String username) ;
}
