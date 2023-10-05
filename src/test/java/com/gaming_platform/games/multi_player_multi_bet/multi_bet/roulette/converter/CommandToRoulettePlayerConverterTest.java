package com.gaming_platform.games.multi_player_multi_bet.multi_bet.roulette.converter;

import com.gaming_platform.commands.CreateBetCommand;
import com.gaming_platform.commands.CreateMultiBetPlayerCommand;
import com.gaming_platform.exceptions.InvalidFieldException;
import com.gaming_platform.exceptions.InvalidPlayerException;
import com.gaming_platform.games.multi_player_multi_bet.roulette.converter.CommandToRoulettePlayerConverter;
import com.gaming_platform.games.multi_player_multi_bet.roulette.converter.RouletteBetFactory;
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

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CommandToRoulettePlayerConverterTest {

    @Mock
    RouletteBetFactory rouletteBetFactory;

    CommandToRoulettePlayerConverter commandToRoulettePlayerConverter;

    @BeforeEach
    void setUp() {
        commandToRoulettePlayerConverter = new CommandToRoulettePlayerConverter(rouletteBetFactory);
    }

    @Test
    public void convertCommandToRoulettePlayer() throws InvalidPlayerException, InvalidFieldException {
        Long gameId = 1L;
        Long playerId = 2L;
        Long betId = 3L;

        CreateBetCommand createBetCommand = generateBetCommand(betId);
        CreateMultiBetPlayerCommand createMultiBetPlayerCommand = generatePlayerCommand(playerId, List.of(createBetCommand));

        Mockito.when(rouletteBetFactory.buildRouletteBet(gameId, playerId, createBetCommand)).thenReturn(generateStraightRouletteBet(gameId, playerId));
        RoulettePlayer player = commandToRoulettePlayerConverter.convertCommandToMultiBetPlayer(gameId, createMultiBetPlayerCommand);

        assertAll("player",
                () -> assertEquals(playerId, player.getPlayerId()),
                () -> assertEquals(gameId, player.getBets().get(0).getGameId()),
                () -> assertEquals(betId, player.getBets().get(0).getBetId()),
                () -> assertEquals(50d, player.getBets().get(0).getAmount()));
    }

    private static CreateMultiBetPlayerCommand generatePlayerCommand(Long playerId, List<CreateBetCommand> createBetCommands) {
        return new CreateMultiBetPlayerCommand(playerId, createBetCommands);
    }

    private static CreateBetCommand generateBetCommand(Long betId) {
        return new CreateBetCommand(betId, "STRAIGHT", 50d, 9);
    }

    private static StraightRouletteBet generateStraightRouletteBet(Long gameId, Long playerId) {
        return StraightRouletteBet.builder().betId(3L).playerId(playerId).gameId(gameId)
                .amount(50d).bet(9).build();
    }

}