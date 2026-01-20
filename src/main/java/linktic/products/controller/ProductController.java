package linktic.products.controller;


import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/products")
public class ProductController {
    private final IProductService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto product) {
        return ResponseEntity.ok(service.createProduct(product));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable Integer Id) {
        return ResponseEntity.ok(service.findProductById(Id));
    }

    @GetMapping("/find-all-products}")
    public ResponseEntity<ResponseDto> findAllProducts() {
        return ResponseEntity.ok(service.findAllProducts());
    }
}
