package linktic.products.service.impl;


import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.dtos.mapper.ProductMapper;
import linktic.products.repository.ProductRepository;
import linktic.products.service.IProductService;
import linktic.products.utils.CodesAndDescriptionResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public ResponseDto createProduct(ProductDto product) {
        log.info("Starting method createProduct()");
        repository.save(mapper.toEntity(product));
        return ResponseDto.builder().code(CodesAndDescriptionResponses.OK.getCode())
                .description(CodesAndDescriptionResponses.OK.getDescription()).build();
    }

    @Override
    public ResponseDto findProductById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto findAllProducts() {
        return null;
    }
}
