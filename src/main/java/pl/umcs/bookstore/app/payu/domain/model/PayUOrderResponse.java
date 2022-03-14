package pl.umcs.bookstore.app.payu.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayUOrderResponse {

    private String orderId;
    private String redirectUri;
    private Status status;
    private String rawResponse;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Status {

        private String statusCode;
    }
}
