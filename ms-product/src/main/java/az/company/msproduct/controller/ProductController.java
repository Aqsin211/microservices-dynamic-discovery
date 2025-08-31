package az.company.msproduct.controller;

import az.company.msproduct.model.dto.response.OrderResponseDTO;
import az.company.msproduct.model.dto.request.ProductRequestDTO;
import az.company.msproduct.model.dto.request.OrderRequestDTO;
import az.company.msproduct.model.dto.response.ProductResponseDTO;
import az.company.msproduct.service.concrete.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static az.company.msproduct.model.enums.SystemMessages.PRODUCT_CREATED;
import static az.company.msproduct.model.enums.SystemMessages.PRODUCT_DELETED;
import static az.company.msproduct.model.enums.SystemMessages.PRODUCT_UPDATED;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public String createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        Long id = productServiceImpl.create(productRequestDTO);
        return format(PRODUCT_CREATED.getMessages(), id);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        return ResponseEntity.ok(productServiceImpl.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(productServiceImpl.getProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) {
        productServiceImpl.updateProduct(id, productRequestDTO);
        return ResponseEntity.ok(PRODUCT_UPDATED.getMessages());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productServiceImpl.deleteProduct(id);
        return ResponseEntity.ok(PRODUCT_DELETED.getMessages());
    }

    @PostMapping("/order")
    @ResponseStatus(CREATED)
    public OrderResponseDTO order(@RequestBody OrderRequestDTO orderRequestDTO) {
        return productServiceImpl.buyProduct(orderRequestDTO);
    }
}
