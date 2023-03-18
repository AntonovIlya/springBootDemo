package springbootdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootDemoApplicationTests {

    private final GenericContainer<?> devapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    private final GenericContainer<?> prodapp = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        devapp.start();
        prodapp.start();
    }

    @Test
    void devTest() {
        ResponseEntity<String> devEntity = restTemplate.getForEntity("http://localhost:" + devapp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(devEntity.getBody(), "Current profile is dev");
    }

    @Test
    void prodTest() {
        ResponseEntity<String> prodEntity = restTemplate.getForEntity("http://localhost:" + prodapp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(prodEntity.getBody(), "Current profile is production");
    }

}

