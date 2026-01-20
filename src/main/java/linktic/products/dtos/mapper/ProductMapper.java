package linktic.products.dtos.mapper;

import linktic.products.dtos.ProductDto;
import linktic.products.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toDto(ProductEntity entity);
    ProductEntity toEntity(ProductDto entity);
}
