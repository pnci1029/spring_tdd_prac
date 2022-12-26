package com.demo.spring.prac.events;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller @RequiredArgsConstructor
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> createEvents(@RequestBody EventDto eventDto) {

        /**
         * dto to Entity 방법 1
         * dto 빌더
         */

//        Event event = Event.builder()
//                .name(eventDto.getName())
//                .maxPrice(eventDto.getMaxPrice())
//                .location(eventDto.getLocation())
//                .free(eventDto.isFree())
//                .closeEnrollmentDateTime(eventDto.getCloseEnrollmentDateTime())
//                .description(eventDto.getDescription())
//                .beginEnrollmentDateTime(eventDto.getBeginEnrollmentDateTime())
//                .limitOfEnrollment(eventDto.getLimitOfEnrollment())
//                .build();

        /**
         * dto to Entity 방법 2
         * 모델매퍼 라이브러리 사용
         */

        Event event = modelMapper.map(eventDto, Event.class);

        Event savedEvent = eventRepository.save(event);
        URI uri = linkTo(EventController.class).slash(savedEvent.getId()).toUri();
        return ResponseEntity.created(uri).body(event);
    }
}
