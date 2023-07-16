package integration.com.gamingPlatform.controller;

import com.gamingPlatform.controller.GameController;
import com.gamingPlatform.service.CreateGameCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static integration.com.gamingPlatform.TestConstants.generateHigherOrLowerGameCommand;

@SpringBootTest(classes = GameController.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void testHigherOrLowerGame() {
        CreateGameCommand createGameCommand = generateHigherOrLowerGameCommand(10f, 100);
        ResponseEntity<CreateGameCommand> responseEntity = this.restTemplate.postForEntity("httpL//localhost:" + port + "/play", String.class);
    }

}
