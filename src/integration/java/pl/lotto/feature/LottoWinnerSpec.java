//package pl.lotto.feature;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import pl.lotto.BaseIntegrationTest;
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//
//import static java.util.concurrent.TimeUnit.SECONDS;
//import static org.awaitility.Awaitility.await;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class LottoWinnerSpec extends BaseIntegrationTest {
//
//    @Autowired
//    protected MockMvc mockMvc;
//
//    @Autowired
//    protected ObjectMapper objectMapper;
//
//    @Test
//    public void should_user_play_and_check_win_after_some_days() throws Exception {
//        // STEP 1: when user input 6 correct numbers (1,2,3,4,5,6) (in range 1-99) to POST /inputNumbers
//        // STEP 2 system returns unique random userLotteryId and returned draw 24-12-2022 to user
//        // given
//
//        // when
//        ResultActions perform = mockMvc.perform(post("/inputNumbers")
//                .content("{\"numbers\":[1,2,3,4,5,6]}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE));
//        // then
//        MvcResult mvcResult = perform
//                .andExpect(status().isOk())
//                .andExpect(content()
//                        .json(
//                                "{\"message\":\"success\"," +
//                                        "\"drawDate\":\"2022-12-24T12:00:00\"}"))
//                .andReturn();
//
//        String json = mvcResult.getResponse().getContentAsString();
//        NumberReceiverResultDto result = objectMapper.readValue(json, NumberReceiverResultDto.class);
//        // STEP 3 system generates random winning numbers (1,2,3,4,5,6) using “lotto” logic for 24-12-2022, winning numbers are generated every saturday at 12:00
//        // when
//
//
//        // STEP 4 user wants to know if won using GET /winners/{userLotteryId} but before draw
//        // given
//        // when
//        // then
//        String dateBeforeDrawingAsText = "2022-12-23T12:00:00";
//        LocalDateTime dateBeforeDrawing = LocalDateTime.parse(dateBeforeDrawingAsText);
//        adjustableClock.setClockToLocalDateTime(dateBeforeDrawing);
//
//        await().atMost(10, SECONDS)
//                .pollInterval(Duration.ofSeconds(1))
//                .until(() -> resultCheckerFacade.checkResult().stream()
//                        .anyMatch(checkedTicket -> checkedTicket.getLotteryId().equals(result.lotteryId())));
//
//
//        ResultActions perform2 = mockMvc.perform(get("/winners/" + result.lotteryId())
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(content().json("""
//                        {"ticketAnnounce":null,"message":"TOO_EARLY"}
//                        """))
//                .andExpect(status().isNoContent());
//
//        // STEP 5 system checkedWinner
//        // given
//        // when
//        // then
//
//        // STEP 6 user wants to know if won using GET /winners/{userLotteryId} after draw
//
//        // given
//        adjustableClock.plusDays(2);
//        await().atMost(30, SECONDS)
//                .pollInterval(Duration.ofSeconds(1))
//                .until(() -> !luckyNumbersGeneratorFacade.retrieve(result.drawDate()).winningNumbers().isEmpty());
//
//        // when
//        ResultActions perform3 = mockMvc.perform(get("/winners/" + result.lotteryId())
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(content().json("""
//                        {"message":"MAIN_PRIZE"}
//                        """))
//                .andExpect(status().isOk());
//        // then
//
//    }
//}
