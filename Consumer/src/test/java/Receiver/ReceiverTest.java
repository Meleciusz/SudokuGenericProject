package Receiver;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
class ReceiverTest {
    @Test
    public void testQueueAvailability() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);

        try (Connection connection = factory.newConnection()) {
            assert true;
        }
    }
}