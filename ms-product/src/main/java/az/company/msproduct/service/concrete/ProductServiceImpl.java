package az.company.msproduct.service.concrete;

import az.company.msproduct.dao.entity.ProductEntity;
import az.company.msproduct.dao.repository.ProductRepository;
import az.company.msproduct.exception.NotFoundException;
import az.company.msproduct.exception.ValidationException;
import az.company.msproduct.model.dto.request.OrderRequestDTO;
import az.company.msproduct.model.dto.request.ProductRequestDTO;
import az.company.msproduct.model.dto.response.OrderResponseDTO;
import az.company.msproduct.model.dto.response.ProductResponseDTO;
import az.company.msproduct.service.abstraction.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static az.company.msproduct.model.enums.StatusEnums.NOT_ENOUGH_STOCK;
import static az.company.msproduct.model.enums.StatusEnums.ORDER_COMPLETE;
import static az.company.msproduct.model.enums.SystemMessages.*;
import static az.company.msproduct.model.mapper.ProductMapper.PRODUCT_MAPPER;
import static java.lang.String.format;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Long create(ProductRequestDTO productRequestDTO) {
        validateProductRequest(productRequestDTO);
        ProductEntity entity = PRODUCT_MAPPER.mapRequestToEntity(productRequestDTO);
        ProductEntity saved = productRepository.save(entity);
        return saved.getId();
    }

    @Override
    public ProductResponseDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(PRODUCT_MAPPER::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException(format(PRODUCT_NOT_FOUND.getMessages(), id)));
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(PRODUCT_MAPPER::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Long id, ProductRequestDTO dto) {
        ProductEntity entity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(format(PRODUCT_NOT_FOUND.getMessages(), id)));

        if (dto.getName() != null && !dto.getName().isBlank()) entity.setName(dto.getName());
        if (dto.getDescription() != null && !dto.getDescription().isBlank()) entity.setDescription(dto.getDescription());
        if (dto.getPrice() != null) entity.setPrice(validatePrice(dto.getPrice()));
        if (dto.getStock() != null) entity.setStock(validateStock(dto.getStock()));

        entity.setUpdatedAt(LocalDateTime.now());
        productRepository.save(entity);
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(format(PRODUCT_NOT_FOUND.getMessages(), id));
        }
        productRepository.deleteById(id);
    }

    @Override
    public OrderResponseDTO buyProduct(OrderRequestDTO orderRequestDTO) {
        ProductEntity product = productRepository.findById(orderRequestDTO.getProductId())
                .orElseThrow(() -> new NotFoundException(
                        format(PRODUCT_NOT_FOUND.getMessages(), orderRequestDTO.getProductId())
                ));

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setProductName(product.getName());
        orderResponseDTO.setQuantity(orderRequestDTO.getQuantity());

        if (product.getStock() >= orderRequestDTO.getQuantity()) {
            product.setStock(product.getStock() - orderRequestDTO.getQuantity());
            productRepository.save(product);

            orderResponseDTO.setStatus(ORDER_COMPLETE);
            orderResponseDTO.setTotalPrice(
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(orderRequestDTO.getQuantity()))
                            .setScale(2, RoundingMode.HALF_UP)
            );
        } else {
            orderResponseDTO.setStatus(NOT_ENOUGH_STOCK);
            orderResponseDTO.setTotalPrice(BigDecimal.ZERO);
        }

        return orderResponseDTO;
    }


    private void validateProductRequest(ProductRequestDTO dto) {
        validatePrice(dto.getPrice());
        validateStock(dto.getStock());
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) <= 0) throw new ValidationException(PRICE_CANNOT_BE_NEGATIVE_OR_ZERO.getMessages());
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    private Integer validateStock(Integer stock) {
        if (stock < 0) throw new ValidationException(STOCK_CANNOT_BE_NEGATIVE.getMessages());
        return stock;
    }
}
