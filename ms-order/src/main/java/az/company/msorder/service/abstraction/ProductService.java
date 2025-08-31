package az.company.msorder.service.abstraction;

import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;

public interface ProductService {
    OrderResponseDTO orderProduct(OrderRequestDTO orderRequestDTO);
}
