//package integration_test.com.gaming_platform.service;
//
//import com.gaming_platform.GamingPlatformApplication;
//import com.gaming_platform.result_dto.SinglePlayerSingleBetGameResult;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import com.gaming_platform.core.service.MultiPlayerMultiBetGameService;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//
//import static integration_test.com.gaming_platform.IntegrationTestConstants.generateHigherOrLowerGameCommand;
//
//@SpringBootTest
//@AutoConfigureMockMvc(addFilters = false)
//@ContextConfiguration(classes = {MultiPlayerMultiBetGameService.class, GamingPlatformApplication.class})
//class GameServiceIntTest {
//
//    @Autowired
//    private MultiPlayerMultiBetGameService gameService;
//
//    public static final double DEFAULT_BET = 10.0d;
//    public static final Random RANDOM = new Random();
//
//    @Test
//    void ReturnRtpFromOneMillionGamesPlayedInTwentyFourParallelThreads() throws ExecutionException, InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(24);
//        final int NUMBER_OF_GAMES = 1000000;
//
//        List<Future<SinglePlayerSingleBetGameResult>> futures = new ArrayList<>();
//
//        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
//            Future<SinglePlayerSingleBetGameResult> future = executor.submit(() -> gameService.playGame(
//                    generateHigherOrLowerGameCommand(DEFAULT_BET, RANDOM.nextInt(1, 100))));
//            futures.add(future);
//        }
//        double sum = 0;
//        for (Future<SinglePlayerSingleBetGameResult> future : futures) {
//            sum += future.get().getBetResult().getAmount();
//        }
//
//        executor.shutdown();
//
//        Double RTP = 100 * sum / (NUMBER_OF_GAMES * DEFAULT_BET);
//        System.out.println("RTP = " +  String.valueOf(RTP).substring(0, 6) + "%");
//    }
//
//}