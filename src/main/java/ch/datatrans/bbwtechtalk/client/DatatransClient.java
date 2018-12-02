package ch.datatrans.bbwtechtalk.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * @author dominik.mengelt@gmail.com
 */
@Component
public class DatatransClient {

    private static final Logger logger = LoggerFactory.getLogger(DatatransClient.class);

    private final static String DATATRANS_BASE_URL = "https://api.sandbox.datatrans.com";
    private final static String TRANSACTION_INIT_API = "/v1/transactions";

    private final RestTemplate restTemplate;

    public DatatransClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String initTransaction(String refno, Long amount, String currency) {

        var initTransactionRequest = new InitTransactionRequest("asdf", amount, currency);
        printAsJson(initTransactionRequest);

        InitTransactionResponse initTransactionResponse =
                restTemplate.postForObject(DATATRANS_BASE_URL + TRANSACTION_INIT_API,
                        createHttpEntity(initTransactionRequest),
                        InitTransactionResponse.class,
                        Collections.emptyMap());

        return initTransactionResponse.getPaymentId();

    }

    private <T> HttpEntity<T> createHttpEntity(T object) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(object,headers);
    }

    private static void printAsJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            logger.info(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
