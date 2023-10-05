package com.gaming_platform.games.multi_player_multi_bet.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.core.model.game.GameType;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.converter.CommandToRouletteGameConverter;
import com.gaming_platform.games.multi_player_multi_bet.roulette.converter.CommandToRoulettePlayerConverter;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RouletteGame;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player_multi_bet.roulette.model.rouletteBet.StraightRouletteBet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToRouletteGameConverterTest {

    CommandToRouletteGameConverter commandToRouletteGameConverter;

    @Mock
    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @BeforeEach
    void setUp() {
        commandToRouletteGameConverter = new CommandToRouletteGameConverter(commandToRoulettePlayerConverter);
    }

    @Test
    public void convertCommandToRouletteGame() throws InvalidPlayerException {
        Long playerId = 2L;
        Long betId = 3L;

        CreateBetCommand createBetCommand = generateBetCommand(betId);
        CreateMultiBetPlayerCommand createMultiBetPlayerCommand = generatePlayerCommand(playerId, List.of(createBetCommand));

        String gameName = GameType.ROULETTE.name();
        CreateMultiPlayerMultiBetGameCommand createGameCommand = generateGameCommand(gameName, List.of(createMultiBetPlayerCommand));
        Mockito.when(commandToRoulettePlayerConverter.convertCommandToMultiBetPlayer(anyLong(), any(CreateMultiBetPlayerCommand.class)))
                .thenReturn(generateRoulettePlayer(playerId));
        RouletteGame rouletteGame = commandToRouletteGameConverter.convert(createGameCommand);

        RoulettePlayer player = rouletteGame.getPlayers().get(0);

        assertAll("game",
                () -> assertEquals(playerId, player.getPlayerId()),
                () -> assertEquals(betId, player.getBets().get(0).getBetId()),
                () -> assertEquals(50d, player.getBets().get(0).getAmount())
        );
    }

    private static CreateMultiPlayerMultiBetGameCommand generateGameCommand(String gameName, List<CreateMultiBetPlayerCommand> createMultiBetPlayerCommands) {
        return new CreateMultiPlayerMultiBetGameCommand(gameName, createMultiBetPlayerCommands);
    }

    private static CreateMultiBetPlayerCommand generatePlayerCommand(Long playerId, List<CreateBetCommand> createBetCommands) {
        return new CreateMultiBetPlayerCommand(playerId, createBetCommands);
    }

    private static CreateBetCommand generateBetCommand(Long betId) {
        return new CreateBetCommand(betId, "STRAIGHT", 50d, 9);
    }

    private static RoulettePlayer generateRoulettePlayer(Long playerId) {
        return new RoulettePlayer(playerId, List.of(generateStraightRouletteBet(playerId)));
    }

    private static StraightRouletteBet generateStraightRouletteBet(Long playerId) {
        return StraightRouletteBet.builder().betId(3L).playerId(playerId)
                .amount(50d).bet(9).build();
    }

}