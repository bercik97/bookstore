package pl.umcs.bookstore.app.payu.domain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import pl.umcs.bookstore.app.payu.domain.auth.PayUAuthToken;
import pl.umcs.bookstore.app.payu.domain.auth.PayUClientAuthenticatorService;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
class PayUAuthConfig {

    private final PayUClientAuthenticatorService payUClientAuthenticatorService;

    @Bean("payuApiRestTemplate")
    public RestTemplate payuRestTemplate() {
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList((httpRequest, bytes, clientHttpRequestExecution) -> {
            final PayUAuthToken payUAuthToken = payUClientAuthenticatorService.authenticate();
            final HttpHeaders headers = httpRequest.getHeaders();
            headers.add("Authorization", payUAuthToken.getTokenType() + " " + payUAuthToken.getAccessToken());
            return clientHttpRequestExecution.execute(httpRequest, bytes);
        }));
        return restTemplate;
    }
}
