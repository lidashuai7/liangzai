package com.cy.pj.service.hlj;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cy.pj.dao.hlj.GuestMapper;
import com.cy.pj.vo.hlj.Guest;
import com.cy.pj.vo.hlj.Order;

@Service
public class GuestServiceImpl implements GuestService {
	@Autowired
	private GuestMapper guestMapper;

	@Override
	public void  addGuest(Guest g) {
		 guestMapper.addGuest(g);
	}

	@Override
	public void insertGuest(Guest g) {
		guestMapper.insertGuest(g);
	}

	@Override
	public Guest selectByIdCard(String id) {
		Guest g = guestMapper.selectByIdCard(id);
		return g;
	}

	@Override
	public void updateByIdCard(String name,String id) {
		// TODO Auto-generated method stub
		guestMapper.updateByIdCard(name,id);
		
		
	}

	@Override
	public Guest selectByIdCard1(String id) {
		Guest g = guestMapper.selectByIdCard1(id);
		return g;
	}

	@Override
	public void updateByIdCard1(String name, String id) {
		guestMapper.updateByIdCard1(name, id);
		
	}

	@Override
	public void addOrder(Order order) {
		
		guestMapper.addOrder(order);
		
	}
	
	@Override
	public void updateByHouseId(Integer houseId) {
		guestMapper.updateByHouseId(houseId);
	}
	

}
