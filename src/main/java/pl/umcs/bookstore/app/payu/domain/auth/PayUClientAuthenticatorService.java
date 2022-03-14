package pl.umcs.bookstore.app.payu.domain.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.umcs.bookstore.app.payu.domain.config.PayUConfigProperties;

@Service
@RequiredArgsConstructor
public class PayUClientAuthenticatorService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();
    private final PayUConfigProperties payUConfiguration;

    @SneakyThrows
    public PayUAuthToken authenticate() {
        AuthTokenRequest authTokenRequest = new AuthTokenRequest(payUConfiguration);
        final ResponseEntity<String> jsonResponse = restTemplate.postForEntity(authTokenRequest.build(), null, String.class);
        return objectMapper.readValue(jsonResponse.getBody(), PayUAuthToken.class);
    }

    private static class AuthTokenRequest {

        private final String authorizationUri;
        private final String clientId;
        private final String clientSecret;
        private final String grantType;

        public AuthTokenRequest(PayUConfigProperties payUConfigProperties) {
            this.authorizationUri = payUConfigProperties.getAuthorizationUri();
            this.clientId = payUConfigProperties.getClientId();
            this.clientSecret = payUConfigProperties.getClientSecret();
            this.grantType = "client_credentials";
        }

        public String build() {
            return String.format("%s?grant_type=%s&client_id=%s&client_secret=%s", authorizationUri, grantType, clientId, clientSecret);
        }
    }
}
