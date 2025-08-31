package az.company.msorder.service.concrete;

import az.company.msorder.exception.ServiceUnavailableException;
import az.company.msorder.model.dto.request.OrderRequestDTO;
import az.company.msorder.model.dto.response.OrderResponseDTO;
import az.company.msorder.service.abstraction.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static az.company.msorder.model.enums.SystemMessages.PRODUCT_SERVICE_NOT_AVAILABLE;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;

    public ProductServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponseDTO orderProduct(OrderRequestDTO orderRequestDTO) {
        try {
            return restTemplate.postForObject(
                    "http://ms-product/products/order",
                    orderRequestDTO,
                    OrderResponseDTO.class
            );
        } catch (Exception e) {
            throw new ServiceUnavailableException(PRODUCT_SERVICE_NOT_AVAILABLE.getMessage());
        }
    }
}