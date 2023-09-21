package com.mkrasucki.Producer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class ProducerApplicationTest {

    @Test
    @DisplayName("Test if the application starts")
    void main() {
        assertThrows(Exception.class, () -> {
            SpringApplication.run(ProducerApplication.class, null);
        });
    }
}