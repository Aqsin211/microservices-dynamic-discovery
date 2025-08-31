package az.company.msproduct.service.abstraction;

import az.company.msproduct.model.dto.request.OrderRequestDTO;
import az.company.msproduct.model.dto.request.ProductRequestDTO;
import az.company.msproduct.model.dto.response.OrderResponseDTO;
import az.company.msproduct.model.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    Long create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO getProduct(Long id);

    List<ProductResponseDTO> getAllProducts();

    void updateProduct(Long id, ProductRequestDTO productRequestDTO);

    void deleteProduct(Long id);

    OrderResponseDTO buyProduct(OrderRequestDTO orderRequestDTO);
}
