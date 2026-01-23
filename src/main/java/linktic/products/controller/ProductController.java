package linktic.products.controller;


import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final IProductService service;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto product) {
        log.info("Starting controller createProduct()");
        return ResponseEntity.ok(service.createProduct(product));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseDto> findProductById(@PathVariable Integer id) {
        log.info("Starting controller findProductById()");
        return ResponseEntity.ok(service.findProductById(id));
    }


    @GetMapping("/find-all-products")
    public ResponseEntity<ResponseDto> findAllProducts() {
        log.info("Starting controller findAllProducts()");
        return ResponseEntity.ok(service.findAllProducts());
    }
}
