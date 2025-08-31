package az.company.msproduct.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static az.company.msproduct.exception.ValidationMessages.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {

    @NotBlank(message = PRODUCT_NAME_CANNOT_BE_BLANK)
    @Size(min = 2, max = 100, message = PRODUCT_NAME_MUST_NOT_EXCEED_30)
    String name;

    @NotNull(message = PRICE_IS_REQUIRED)
    @DecimalMin(value = "0.01", message = PRICE_MUST_BE_GREATER_THAN_0)
    @DecimalMax(value = "100000.00", message = PRICE_MUST_NOT_EXCEED_100000)
    BigDecimal price;

    @NotNull(message = STOCK_IS_REQUIRED)
    @Min(value = 0, message = STOCK_CANNOT_BE_NEGATIVE)
    @Max(value = 10000, message = STOCK_CANNOT_EXCEED_10000)
    Integer stock;

    @NotBlank(message = DESCRIPTION_CANNOT_BE_BLANK)
    @Size(max = 255, message = DESCRIPTION_MUST_NOT_EXCEED_255)
    String description;
}
