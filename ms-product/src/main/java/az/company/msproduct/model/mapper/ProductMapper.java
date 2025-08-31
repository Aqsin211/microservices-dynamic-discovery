package az.company.msproduct.model.mapper;

import az.company.msproduct.dao.entity.ProductEntity;
import az.company.msproduct.model.dto.request.ProductRequestDTO;
import az.company.msproduct.model.dto.response.ProductResponseDTO;

import java.time.LocalDateTime;

public enum ProductMapper {
    PRODUCT_MAPPER;

    public ProductEntity mapRequestToEntity(ProductRequestDTO productRequestDTO) {
        return ProductEntity.builder()
                .description(productRequestDTO.getDescription())
                .name(productRequestDTO.getName())
                .price(productRequestDTO.getPrice())
                .stock(productRequestDTO.getStock())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public ProductResponseDTO mapEntityToResponse(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }
}
