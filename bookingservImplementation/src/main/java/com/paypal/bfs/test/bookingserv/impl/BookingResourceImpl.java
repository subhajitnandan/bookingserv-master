package com.paypal.bfs.test.bookingserv.impl;

import java.util.List;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.dao.BookingDAO;
@RestController
public class BookingResourceImpl implements BookingResource {
	@Autowired
	BookingDAO dao;
	@Autowired
	Cache<String, String> cache;
	@Override
	public Booking create(Booking booking,String token) {
		if(cache.get(token)==null) {
			synchronized (cache) {
				if(cache.get(token)==null) {
					Booking bkng=dao.save(booking);
					cache.put(token, bkng.getId()+":"+(bkng.getAddress()!=null?bkng.getAddress().getId():0));
					return bkng;
				}
				else {
					return getBooking(booking, token);
				}
			}

		}
		else {
			return getBooking(booking, token);
		}

	}
	Booking getBooking(Booking booking,String token) {
		String[]ids=cache.get(token).split(":");
		booking.setId(Integer.parseInt(ids[0]));
		if(booking.getAddress()!=null)
			booking.getAddress().setId(Integer.parseInt(ids[1]));
		return booking;
	}
	@Override
	public List<Booking> getAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
}
