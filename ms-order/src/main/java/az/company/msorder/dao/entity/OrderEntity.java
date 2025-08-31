package az.company.msorder.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@FieldDefaults(level = PRIVATE)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "product_name")
    String productName;
    Integer quantity;

    @Column(name = "total_price")
    BigDecimal totalPrice;
    String status;

    @Column(name = "payment_type")
    String paymentType;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}
