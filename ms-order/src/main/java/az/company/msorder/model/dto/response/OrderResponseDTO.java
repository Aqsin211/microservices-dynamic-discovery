package az.company.msorder.model.dto.response;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDTO {
    Long orderId;
    Long productId;
    String productName;
    Integer quantity;
    BigDecimal totalPrice;
    String status;
    String paymentType;
}
