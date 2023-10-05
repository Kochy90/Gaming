package integration_test.com.gaming_platform.controller;

import com.gaming_platform.GamingPlatformApplication;
import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateSingleBetPlayerCommand;
import com.gaming_platform.commands.CreateSinglePlayerSingleBetGameCommand;
import com.gaming_platform.controller.GameController;
import com.gaming_platform.core.service.SinglePlayerSingleBetGameService;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerBet;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerGame;
import com.gaming_platform.games.single_player_single_bet.higher_or_lower.model.HigherOrLowerPlayer;
import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;
import java.util.Random;

import static integration_test.com.gaming_platform.IntegrationTestConstants.createHigherOrLowerGameCommandBuilder;
import static integration_test.com.gaming_platform.IntegrationTestConstants.generateHigherOrLowerBetCommandBuilder;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest(classes = {GameController.class, GamingPlatformApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    SinglePlayerSingleBetGameService<HigherOrLowerGame, HigherOrLowerPlayer, HigherOrLowerBet> singlePlayerSingleBetGameService;

    @Test
    void testHigherOrLowerGamePlayMethod() {
        CreateBetCommand betCommand = generateHigherOrLowerBetCommandBuilder().bet(99).amount(50.0).build();
        CreateSingleBetPlayerCommand playerCommand = new CreateSingleBetPlayerCommand(new Random().nextLong(), betCommand);
        CreateSinglePlayerSingleBetGameCommand gameCommand = createHigherOrLowerGameCommandBuilder().createSingleBetPlayerCommand(playerCommand).build();

        String rootUrl = "http://localhost:" + port + "/game";
        WebClient.create(rootUrl);

        ResponseEntity<SinglePlayerSingleBetGameResult> responseEntity = this.restTemplate.postForEntity(
                rootUrl + "/single-player-single-bet/play",gameCommand, SinglePlayerSingleBetGameResult.class);

        System.out.println(responseEntity.getStatusCode());
        System.out.println(responseEntity.getBody());
        System.out.println(responseEntity.getHeaders());
        System.out.println("Roll: " + Objects.requireNonNull(responseEntity.getBody()).getRoll());
        System.out.println("Bet Result: " + responseEntity.getBody().getBetResult().getAmount());
        System.out.println("Game Id: " + responseEntity.getBody().getGameId());
        System.out.println("Player Id: " + responseEntity.getBody().getPlayerId());
    }

}
