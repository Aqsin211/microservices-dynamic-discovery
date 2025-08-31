package az.company.msproduct.model.dto.response;

import az.company.msproduct.model.enums.StatusEnums;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDTO {
    String productName;
    Integer quantity;
    BigDecimal totalPrice;
    StatusEnums status;
}
