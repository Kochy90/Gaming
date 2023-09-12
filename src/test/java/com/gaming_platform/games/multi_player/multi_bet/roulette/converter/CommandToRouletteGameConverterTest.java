package com.gaming_platform.games.multi_player.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiPlayerMultiBetGameCommand;
import com.gaming_platform.commands.CreatePlayerCommand;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.Game;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.Roulette;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.RoulettePlayer;
import com.gaming_platform.games.multi_player.multi_bet.roulette.model.rouletteBet.StraightRouletteBet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToRouletteGameConverterTest {

    @Autowired
    CommandToRouletteGameConverter commandToRouletteGameConverter;

    @Mock
    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @Test
    public void convertCommandToRouletteGame() throws InvalidPlayerException, NoSuchFieldException, IllegalAccessException {
        Long playerId = 2L;
        Long betId = 3L;
        CreateBetCommand createBetCommand = generateBetCommand(betId);
        CreatePlayerCommand createPlayerCommand = generatePlayerCommand(playerId, List.of(createBetCommand));

        String gameName = Game.ROULETTE.name();
        CreateMultiPlayerMultiBetGameCommand createGameCommand = generateGameCommand(gameName, List.of(createPlayerCommand));
        Roulette roulette = commandToRouletteGameConverter.convert(createGameCommand);
//        Mockito.when(commandToRoulettePlayerConverter.convertCommandToMultiBetPlayer(roulette.getGameId(), createPlayerCommand)).thenReturn(generateRoulettePlayer(playerId));

        RoulettePlayer player = roulette.getPlayers().get(0);

        assertAll("game",
                () -> assertEquals(playerId, player.getPlayerId()),
                () -> assertEquals(betId, player.getBets().get(0).getBetId()),
                () -> assertEquals(50d, player.getBets().get(0).getAmount())
        );
    }

    private static CreateMultiPlayerMultiBetGameCommand generateGameCommand(String gameName, List<CreatePlayerCommand> createPlayerCommands) {
        return new CreateMultiPlayerMultiBetGameCommand(gameName, createPlayerCommands);
    }

    private static CreatePlayerCommand generatePlayerCommand(Long playerId, List<CreateBetCommand> createBetCommands) {
        return new CreatePlayerCommand(playerId, createBetCommands);
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