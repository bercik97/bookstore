package pl.umcs.bookstore.app.payu.domain.model;

import lombok.Value;
import pl.umcs.bookstore.app.book.domain.Book;
import pl.umcs.bookstore.app.payu.domain.command.CreatePayUPaymentCommand;
import pl.umcs.bookstore.app.payu.domain.config.PayUConfigProperties;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class PayUOrderRequest {

    String continueUrl;
    String customerIp;
    String merchantPosId;
    String description;
    String currencyCode;
    String totalAmount;
    PayUBuyer buyer;
    List<PayUProduct> products;

    public static PayUOrderRequest of(CreatePayUPaymentCommand command, PayUConfigProperties configProperties) {
        return new PayUOrderRequest(configProperties.getContinueUrl(),
                command.getCustomerIp(),
                configProperties.getMerchantPosId(),
                configProperties.getDescription(),
                "PLN",
                String.valueOf(command.getProductPrice()),
                PayUBuyer.of(command.getEmail(), "pl"),
                convert(command.getBooks()));
    }

    private static List<PayUProduct> convert(List<Book> books) {
        return books.stream()
                .map(book -> {
                    String productName = String.format("%s - %s", book.getTitle(), book.getAuthor());
                    return PayUProduct.of(productName, String.valueOf(Double.valueOf(book.getPrice()).intValue() * 100), String.valueOf(1));
                })
                .collect(Collectors.toList());
    }

    @Value(staticConstructor = "of")
    static class PayUBuyer {

        String email;
        String language;
    }

    @Value(staticConstructor = "of")
    static class PayUProduct {

        String name;
        String unitPrice;
        String quantity;
    }

}
