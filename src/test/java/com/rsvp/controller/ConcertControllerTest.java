package com.rsvp.controller;

import com.google.gson.*;
import com.rsvp.dto.ConcertDto;
import com.rsvp.dto.ResponseDto;
import com.rsvp.mapper.ConcertMapper;
import com.rsvp.model.Concert;
import com.rsvp.service.impl.ConcertServiceImpl;
import com.rsvp.util.DateAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ConcertController.class)
public class ConcertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ConcertServiceImpl concertService;

    @MockBean
    ConcertMapper concertMapper;

    private List<Concert> concertList = new ArrayList<>();
    private List<ConcertDto> concertDtoList = new ArrayList<>();
    private Concert concert = new Concert();
    private ConcertDto concertDto = new ConcertDto();

    @BeforeEach
    void init() {
        concertDto = new ConcertDto("Test Concert", LocalDateTime.now());

        BeanUtils.copyProperties(concertDto, concert);
        concert.setId(UUID.randomUUID());
        concert.setName(concertDto.getConcertName());
        concert.setActive(true);

        concertDtoList.add(new ConcertDto("Test Concert 1", LocalDateTime.now()));
        concertDtoList.add(new ConcertDto("Test Concert 2", LocalDateTime.now()));
        concertDtoList.add(new ConcertDto("Test Concert 3", LocalDateTime.now()));

        Concert concertData = new Concert();
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 1");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 2");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
        concertData.setId(UUID.randomUUID());
        concertData.setName("Test Concert 3");
        concertData.setConcertDate(LocalDateTime.now());
        concertData.setActive(true);
        concertList.add(concertData);
    }

    @Test
    void givenConcertData_whenCallSave_thenDataIsSaved() throws Exception {
        ResponseDto response = new ResponseDto("OK", concert);

        when(concertMapper.dtoToEntity(concertDto)).thenReturn(concert);
        when(concertService.addConcert(concertDto)).thenReturn(response);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new DateAdapter()).create();
        String reqBody = gson.toJson(concertDto);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/concert")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(reqBody)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void getAllConcert_thenReturnConcertData() throws Exception {
        ResponseDto response = new ResponseDto("OK", concertDtoList);
        when(concertService.getAllConcert()).thenReturn(response);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/concert")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        JsonObject jsonObject = JsonParser.parseString(result.getResponse().getContentAsString()).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
        assertEquals(response.getMessage(), jsonObject.get("message").getAsString());
        assertEquals(3, jsonArray.size());
    }
}
