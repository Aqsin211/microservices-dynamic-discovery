package az.company.msproduct.model.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {
    Long id;
    String name;
    BigDecimal price;
    Integer stock;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
