package integration_test.com.gaming_platform.controller;

import com.gaming_platform.GamingPlatformApplication;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.controller.GameController;
import com.gaming_platform.service.GameService;
import integration_test.com.gaming_platform.IntegrationTestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = {GameController.class, GamingPlatformApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    GameService gameService;

    @Test
    void testHigherOrLowerGamePlayMethod() {
        CreateSinglePlayerSingleBetGameCommand command = IntegrationTestConstants.generateHigherOrLowerGameCommand(10.0, 99);
        WebClient client = WebClient.create("http://localhost:" + port + "/game");

        ResponseEntity<Double> responseEntity = this.restTemplate.postForEntity("http://localhost:" + port + "/game/play", command, Double.class);
        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
    }

}
