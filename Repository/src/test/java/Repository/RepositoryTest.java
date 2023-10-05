package Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @org.junit.jupiter.api.Test
    public void synchronizedLastAssignedIDTest() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        Repository method = new Repository();

        IntStream.range(0, 1000)
                .forEach(count -> service.submit(method::synchronizedLastAssignedID));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);

        assertEquals(1000, method.getID());
    }
}