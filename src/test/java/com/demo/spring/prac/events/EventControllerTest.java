package com.demo.spring.prac.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;        // mock 빈으로 repository 저장을 하지만
                                            // 널값으로 들어가기 때문에 값 조회 불가능
                                            // -> Mockito 메소드로 널 일때 when - then 지정

    @Test
    void createEvent() throws Exception{
        Event target = Event.builder()
                .id(1)
                .basePrice(1000)
                .beginEnrollmentDateTime(LocalDateTime.now())
                .eventStatus(EventStatus.BEGAN_ENROLLMENT)
                .description("테스트")
                .free(true)
                .location("Seoul")
                .closeEnrollmentDateTime(LocalDateTime.now())
                .maxPrice(10000)
                .name("김김김")
                .build();
        target.setId(1);
        Mockito.when(eventRepository.save(target)).thenReturn(target);

        mockMvc.perform(post("/api/events")        //post요청
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)          // 하이퍼미디어 제이슨 Json + Hal
                        .content(objectMapper.writeValueAsString(target))
                )
                .andDo(print())                               // 요청 응답 찍어보기
                .andExpect(status().is(201))           //요청 후 http 상태 기대값 입력
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,"application/hal+json"))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_VALUE))
        ;

    }

}
