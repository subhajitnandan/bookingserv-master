package com.paypal.bfs.test.bookingserv.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingResource {
    /**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
    @RequestMapping(name="/v1/bfs/booking",method = RequestMethod.POST)
    Booking create(@Valid @RequestBody Booking booking,@RequestHeader(value = "token") String token);

    @RequestMapping(name="/v1/bfs/booking",method = RequestMethod.GET)
    List<Booking> getAll();
}
