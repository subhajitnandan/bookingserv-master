package com.paypal.bfs.test.bookingserv.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingDAO extends JpaRepository<Booking, Integer> {

}
