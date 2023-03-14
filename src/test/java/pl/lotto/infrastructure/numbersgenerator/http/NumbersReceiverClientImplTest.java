package pl.lotto.infrastructure.numbersgenerator.http;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.get;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static wiremock.org.hamcrest.MatcherAssert.assertThat;


@Import(ConfigProperties.class)
@EnableConfigurationProperties(value = ConfigProperties.class)
@ContextConfiguration(classes = ConfigProperties.class)
@TestPropertySource("classpath:application-properties.yml")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
public class NumbersReceiverClientImplTest {

    RestTemplate restTemplate;
    ResponseEntity response;
    WireMockServer wireMockServer;

    @Autowired
    private ConfigProperties configProperties;

    @BeforeEach
    public void setup() {
        restTemplate = new RestTemplate();
        response = null;
        wireMockServer = new WireMockServer(wireMockConfig().port(configProperties.getPort()));
        wireMockServer.start();
wireMockServer.resetAll();
    }

//    @Test
//    @Order(2)
//    public void givenWireMockAdminEndpoint_whenGetWithoutParams_thenVerifyRequest() {
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        response = restTemplate.getForEntity(createHttpLink()+"/", String.class);
//
//        assertThat("Verify Response Body", response.getBody().toString().contains("mappings"));
//        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));
//        wireMockServer.stop();
//    }


//    @Test
//    @Order(1)
//    public void testResourceApi() {
//        System.out.println(configProperties.getHost());
//        wireMockServer.stubFor(
//                get(WireMock.urlEqualTo("/"))
//                        .willReturn(aResponse()
//                                .withStatus(HttpStatus.OK.value())
//                                .withHeader("Content-Type", TEXT_PLAIN_VALUE)
//                                .withBody("test resource")));
//
//        response = restTemplate.getForEntity(createHttpLink(), String.class);
//
//        assertThat("Verify Response Body", response.getBody().toString().contains("test resource"));
//        assertThat("Verify Status Code", response.getStatusCode().equals(HttpStatus.OK));
//
//        wireMockServer.stop();
//    }

    private String createHttpLink() {
        StringBuilder httpLink = new StringBuilder();
        httpLink.append("http://");
        httpLink.append(configProperties.getHost());
        httpLink.append(":");
        httpLink.append(configProperties.getPort());
        httpLink.append("");
        return httpLink.toString();
    }
}