package az.company.msorder.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemMessages {
    PRODUCT_SERVICE_NOT_AVAILABLE("Product service is not available"),
    ORDER_NOT_FOUND("Order not found with id: %s"),
    ORDER_DELETED("Order deleted"),
    ORDER_CREATED("Order created with id: %s");
    private final String message;
}
