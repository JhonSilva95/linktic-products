package linktic.products.service.impl;


import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.dtos.mapper.ProductMapper;
import linktic.products.entities.ProductEntity;
import linktic.products.exceptions.BusinessException;
import linktic.products.repository.ProductRepository;
import linktic.products.service.IProductService;
import linktic.products.utils.CodesAndDescriptionResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() -> new BusinessException(CodesAndDescriptionResponses.PRODUCT_NOT_FOUND.getDescription(),
                        CodesAndDescriptionResponses.PRODUCT_NOT_FOUND.getCode()));

        ProductDto dto = mapper.toDto(entity);
        return ResponseDto.builder().code(CodesAndDescriptionResponses.OK.getCode())
                .description(CodesAndDescriptionResponses.OK.getDescription())
                .content(dto).build();
    }

    @Override
    public ResponseDto findAllProducts() {
        List<ProductEntity> productsEntity = repository.findAll();

        if (productsEntity.isEmpty()) {
            throw new BusinessException(CodesAndDescriptionResponses.PRODUCTS_NOT_FOUNDS.getDescription(),
                    CodesAndDescriptionResponses.PRODUCTS_NOT_FOUNDS.getCode());
        }

        return ResponseDto.builder()
                .code(CodesAndDescriptionResponses.OK.getCode())
                .description(CodesAndDescriptionResponses.OK.getDescription())
                .content(mapper.toDtoList(productsEntity))
                .build();
    }
}
