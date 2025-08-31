package az.company.msorder.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static az.company.msorder.exception.ValidationMessages.PAYMENT_TYPE_IS_REQUIRED;
import static az.company.msorder.exception.ValidationMessages.PRODUCT_ID_CANNOT_BE_NULL;
import static az.company.msorder.exception.ValidationMessages.QUANTITY_CANNOT_EXCEED_100;
import static az.company.msorder.exception.ValidationMessages.QUANTITY_MUST_BE_AT_LEAST_1;
import static az.company.msorder.exception.ValidationMessages.QUANTITY_IS_REQUIRED;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDTO {

    @NotNull(message = PRODUCT_ID_CANNOT_BE_NULL)
    Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    @Min(value = 1, message = QUANTITY_MUST_BE_AT_LEAST_1)
    @Max(value = 100, message = QUANTITY_CANNOT_EXCEED_100)
    Integer quantity;

    @NotNull(message = PAYMENT_TYPE_IS_REQUIRED)
    String paymentType;
}
