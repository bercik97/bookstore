package pl.umcs.bookstore.app.payu.domain.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "payu")
public class PayUConfigProperties {

    private String continueUrl;
    private String description;
    private String clientId;
    private String clientSecret;
    private String authorizationUri;
    private String merchantPosId;
    private String orderUrl;
}
