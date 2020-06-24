package com.cy.pj.service.xbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.pj.dao.xbc.passwordMapper;
import com.cy.pj.vo.xbc.PassWord;

import ch.qos.logback.core.joran.conditional.IfAction;
@Service
public class passWordServiceImpl implements passWordService {
@Autowired
passwordMapper mapper;
	@Override
	public void pws(PassWord p) {
			mapper.updateByUsername(p);
		
	}

}
