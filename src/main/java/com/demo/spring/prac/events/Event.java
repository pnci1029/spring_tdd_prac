package com.demo.spring.prac.events;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter @EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    @Enumerated(value = EnumType.STRING)
    private EventStatus eventStatus;
}
