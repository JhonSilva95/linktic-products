package linktic.products.mapper;

import linktic.products.dtos.ProductDto;
import linktic.products.dtos.mapper.ProductMapper;
import linktic.products.entities.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTests {

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Test
    void toDto_ShouldMapAllFields() {
        ProductEntity entity = new ProductEntity();
        entity.setId(1);
        entity.setName("Celular");
        entity.setPrice(500000L);

        ProductDto dto = mapper.toDto(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
    }

    @Test
    void toDtoList_ShouldMapList() {
        ProductEntity entity = new ProductEntity();
        entity.setName("Tablet");
        List<ProductEntity> entities = List.of(entity);

        List<ProductDto> dtos = mapper.toDtoList(entities);

        assertEquals(1, dtos.size());
        assertEquals("Tablet", dtos.get(0).getName());
    }
}
