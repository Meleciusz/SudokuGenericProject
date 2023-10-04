package Model.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerApplicationTest {

    @Test
    @DisplayName("Test if application throws an exception")
    void main() {
        assertThrows(Exception.class, () -> {
            SpringApplication.run(ConsumerApplication.class, null);
        });
    }
}