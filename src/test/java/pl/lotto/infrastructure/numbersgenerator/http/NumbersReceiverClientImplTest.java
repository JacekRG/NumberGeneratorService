package pl.lotto.infrastructure.numbersgenerator.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NumbersReceiverClientImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Test
    void shouldReturnDrawDateWhenRestTemplateReturnsOkStatus() {
        // given
        NumbersReceiverClientImpl client = new NumbersReceiverClientImpl(restTemplate);
        LocalDateTime exemplaryDate = LocalDateTime.parse("2022-12-24T12:00:00");
        DrawDateDto expectedDrawDateDto = new DrawDateDto(exemplaryDate);
        ResponseEntity<DrawDateDto> response = new ResponseEntity<>(expectedDrawDateDto, HttpStatus.OK);
        given(restTemplate.getForEntity(any(String.class), eq(DrawDateDto.class))).willReturn(response);

        // when
        DrawDateDto actualDrawDateDto = client.specifyDrawDate();

        // then
        assertEquals(expectedDrawDateDto, actualDrawDateDto);
    }
//
//    @Test
//    void shouldThrowExceptionWhenRestTemplateReturnsNotFoundStatus() {
//        // given
//        NumbersReceiverClientImpl client = new NumbersReceiverClientImpl(restTemplate);
//        given(restTemplate.getForEntity(any(String.class), eq(DrawDateDto.class))).willThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
//
//
//    }

}