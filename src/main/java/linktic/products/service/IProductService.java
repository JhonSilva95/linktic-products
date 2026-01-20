package linktic.products.service;

import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;

public interface IProductService {
    ResponseDto createProduct(ProductDto product);

    ResponseDto findProductById(Integer id);

    ResponseDto findAllProducts();
}
