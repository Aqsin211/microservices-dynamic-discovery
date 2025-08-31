package az.company.msproduct.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SystemMessages {
    STOCK_CANNOT_BE_NEGATIVE("Stock cannot be negative or zero"),
    PRICE_CANNOT_BE_NEGATIVE_OR_ZERO("Price cannot be negative"),
    PRODUCT_DELETED("Product deleted"),
    PRODUCT_UPDATED("Product updated"),
    PRODUCT_NOT_FOUND("Product not found with id: %S"),
    PRODUCT_CREATED("Product created with id: %s");
    private final String messages;
}
