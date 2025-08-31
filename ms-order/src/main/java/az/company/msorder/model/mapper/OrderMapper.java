package az.company.msorder.model.mapper;

import az.company.msorder.dao.entity.OrderEntity;
import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;

import java.time.LocalDateTime;

public enum OrderMapper {
    ORDER_MAPPER;

    public OrderEntity mapToEntity(OrderRequestDTO orderRequestDTO, OrderResponseDTO productResponseDTO) {
        return OrderEntity.builder()
                .productId(orderRequestDTO.getProductId())
                .productName(productResponseDTO.getProductName())
                .quantity(orderRequestDTO.getQuantity())
                .totalPrice(productResponseDTO.getTotalPrice())
                .status(productResponseDTO.getStatus())
                .paymentType(orderRequestDTO.getPaymentType())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public OrderResponseDTO mapEntityToResponse(OrderEntity orderEntity) {
        return OrderResponseDTO.builder()
                .orderId(orderEntity.getId())
                .productId(orderEntity.getProductId())
                .productName(orderEntity.getProductName())
                .quantity(orderEntity.getQuantity())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getStatus())
                .paymentType(orderEntity.getPaymentType())
                .build();
    }
}
