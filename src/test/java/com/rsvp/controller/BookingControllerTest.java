package com.rsvp.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsvp.dto.BookingDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.mapper.BookingMapper;
import com.rsvp.model.Booking;
import com.rsvp.model.Concert;
import com.rsvp.service.impl.BookingServiceImpl;
import com.rsvp.util.DateAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    BookingServiceImpl bookingService;

    @MockBean
    BookingMapper bookingMapper;

    private Concert concert = new Concert();
    private Booking booking = new Booking();
    private BookingDto bookingDto = new BookingDto();
    private List<Booking> bookingList = new ArrayList<>();

    @BeforeEach
    void init() {
        concert = new Concert();
        concert.setId(UUID.randomUUID());
        concert.setName("Test Concert");
        concert.setConcertDate(LocalDateTime.now());
        concert.setActive(true);

        booking = new Booking();
        booking.setConcert(concert);
        booking.setId(UUID.randomUUID());
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingQty(7);
        booking.setActive(true);

        bookingDto = new BookingDto(booking.getId(), booking.getBookingDate(), booking.getBookingQty());

        bookingList.add(booking);
    }

    @Test
    void givenConcertData_whenCallSave_thenDataIsSaved() throws Exception {
        ResponseDto response = new ResponseDto();
        response.setMessage("OK");
        response.setData(booking);

        when(bookingMapper.entityToDto(booking)).thenReturn(bookingDto);
        when(bookingService.createBooking(bookingDto)).thenReturn(response);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateAdapter()).create();
        String reqBody = gson.toJson(bookingDto);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/booking")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqBody)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }
}
