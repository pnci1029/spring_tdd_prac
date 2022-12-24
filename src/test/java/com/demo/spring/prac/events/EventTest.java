package com.demo.spring.prac.events;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EventTest {

    @Test
    void builder() {
        Event target = Event.builder().build();
        assertThat(target).isNotNull();
    }

    @Test
    void javaBean() {
        Event target = new Event();
        target.setBasePrice(1000);
        target.setName("히히히");

        assertThat(target.getBasePrice()).isEqualTo(1000);
        assertThat(target.getName()).isEqualTo("히히히");
    }

}