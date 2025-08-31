package az.company.msproduct.model.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static az.company.msproduct.exception.ValidationMessages.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDTO {
    @NotNull(message = PRODUCT_ID_CANNOT_BE_NULL)
    Long productId;

    @NotNull(message = QUANTITY_IS_REQUIRED)
    @Min(value = 1, message = QUANTITY_MUST_AT_LEAST_BE_1)
    @Max(value = 100, message = QUANTITY_CANNOT_EXCEED_100)
    Integer quantity;
}
