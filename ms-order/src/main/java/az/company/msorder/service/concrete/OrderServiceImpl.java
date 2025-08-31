package az.company.msorder.service.concrete;

import az.company.msorder.dao.repository.OrderRepository;
import az.company.msorder.exception.NotFoundException;
import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;
import az.company.msorder.service.abstraction.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.company.msorder.model.enums.SystemMessages.ORDER_NOT_FOUND;
import static az.company.msorder.model.mapper.OrderMapper.ORDER_MAPPER;
import static java.lang.String.format;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductServiceImpl productServiceImpl;

    public OrderServiceImpl(OrderRepository orderRepository, ProductServiceImpl productServiceImpl) {
        this.orderRepository = orderRepository;
        this.productServiceImpl = productServiceImpl;
    }

    @Override
    public Long createOrder(OrderRequestDTO orderRequestDTO) {
        OrderResponseDTO orderResponseDTO = productServiceImpl.orderProduct(orderRequestDTO);
        return orderRepository.save(ORDER_MAPPER.mapToEntity(orderRequestDTO, orderResponseDTO)).getId();
    }

    @Override
    public OrderResponseDTO getOrder(Long orderId) {
        return orderRepository
                .findById(orderId)
                .map(ORDER_MAPPER::mapEntityToResponse)
                .orElseThrow(
                        () -> new NotFoundException(
                                format(ORDER_NOT_FOUND.getMessage(), orderId)
                        )
                );
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository
                .findAll()
                .stream()
                .map(ORDER_MAPPER::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new NotFoundException(
                    format(ORDER_NOT_FOUND.getMessage(), orderId)
            );
        }
    }
}
