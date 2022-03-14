package pl.umcs.bookstore.app.payu.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.umcs.bookstore.app.payu.domain.command.CreatePayUPaymentCommand;
import pl.umcs.bookstore.app.payu.domain.config.PayUConfigProperties;
import pl.umcs.bookstore.app.payu.domain.model.PayUOrderRequest;
import pl.umcs.bookstore.app.payu.domain.model.PayUOrderResponse;

@Service
public class PayUAPIService {

    private final ObjectMapper objectMapper;
    private final PayUConfigProperties payUConfig;
    private final RestTemplate restTemplate;

    public PayUAPIService(PayUConfigProperties payUConfig,
                          @Qualifier(value = "payuApiRestTemplate") RestTemplate restTemplate) {
        this.objectMapper = new ObjectMapper();
        this.payUConfig = payUConfig;
        this.restTemplate = restTemplate;
    }

    @SneakyThrows
    public PayUOrderResponse makePayment(CreatePayUPaymentCommand command) {
        PayUOrderRequest request = PayUOrderRequest.of(command, payUConfig);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(payUConfig.getOrderUrl(), request, String.class);
        return objectMapper.readValue(jsonResponse.getBody(), PayUOrderResponse.class);
    }
}
