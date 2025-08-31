package az.company.msorder.controller;

import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;
import az.company.msorder.service.concrete.OrderServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static az.company.msorder.model.enums.SystemMessages.ORDER_CREATED;
import static az.company.msorder.model.enums.SystemMessages.ORDER_DELETED;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderServiceImpl orderServiceimpl;

    public OrderController(OrderServiceImpl orderServiceimpl) {
        this.orderServiceimpl = orderServiceimpl;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String create(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Long id = orderServiceimpl.createOrder(orderRequestDTO);
        return format(ORDER_CREATED.getMessage(), id);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAll() {
        return ResponseEntity.ok(orderServiceimpl.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> get(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(orderServiceimpl.getOrder(orderId));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> delete(@PathVariable("orderId") Long orderId) {
        orderServiceimpl.deleteOrder(orderId);
        return ResponseEntity.ok(ORDER_DELETED.getMessage());
    }
}
