package com.demo.spring.prac.events;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void createEvent() throws Exception{
        mockMvc.perform(post("/api/events")        //post요청
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)          // 하이퍼미디어 제이슨 Json + Hal
                )
                .andExpect(status().is(201));           //요청 후 http 상태 기대값 입력
    }

}
