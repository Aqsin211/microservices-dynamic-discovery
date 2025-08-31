package az.company.msorder.service.abstraction;

import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    Long createOrder(OrderRequestDTO orderRequestDTO);

    OrderResponseDTO getOrder(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    void deleteOrder(Long orderId);
}
