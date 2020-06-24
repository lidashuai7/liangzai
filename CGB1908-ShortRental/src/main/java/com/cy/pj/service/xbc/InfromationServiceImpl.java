package com.cy.pj.service.xbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.dao.xbc.InfromationMapper;
import com.cy.pj.vo.xbc.PersonalInformation;
@Service
public class InfromationServiceImpl  implements InfromationService{
@Autowired
InfromationMapper mapper;

	@Override
	public void insert(PersonalInformation e) {
		PersonalInformation personalInformation = mapper.selectByUsername(e.getUsername());
		if(personalInformation == null) {
			mapper.insert(e);
		}else {
			mapper.updateByUsername(e);
		}
	
	}
	
	public PersonalInformation selectPersonalInformation(String username) {
		return mapper.selectByUsername(username);
	}

}
