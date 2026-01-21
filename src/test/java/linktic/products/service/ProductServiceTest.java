package linktic.products.service;

import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.dtos.mapper.ProductMapper;
import linktic.products.entities.ProductEntity;
import linktic.products.exceptions.BusinessException;
import linktic.products.repository.ProductRepository;
import linktic.products.service.impl.ProductServiceImpl;
import linktic.products.utils.CodesAndDescriptionResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private ProductEntity entity;
    private ProductDto dto;

    @BeforeEach
    void setUp() {
        entity = new ProductEntity();
        dto = ProductDto.builder().id(1).name("Test Product").build();
    }

    @Test
    void createProduct_ShouldReturnSuccess() {
        when(mapper.toEntity(any(ProductDto.class))).thenReturn(entity);
        when(repository.save(any(ProductEntity.class))).thenReturn(entity);

        ResponseDto response = productService.createProduct(dto);

        assertNotNull(response);
        assertEquals(CodesAndDescriptionResponses.OK.getCode(), response.getCode());
        verify(repository, times(1)).save(any());
    }

    @Test
    void findProductById_WhenExists_ShouldReturnProduct() {
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ResponseDto response = productService.findProductById(1);

        assertEquals(0, response.getCode());
        assertNotNull(response.getContent());
        verify(repository).findById(1);
    }

    @Test
    void findProductById_WhenNotExists_ShouldThrowBusinessException() {
        when(repository.findById(99)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.findProductById(99);
        });

        assertEquals(3, Integer.parseInt(exception.getCode()));
        assertEquals(CodesAndDescriptionResponses.PRODUCT_NOT_FOUND.getDescription(), exception.getMessage());
    }

    @Test
    void findAllProducts_WhenListHasData_ShouldReturnList() {
        List<ProductEntity> list = List.of(entity);
        when(repository.findAll()).thenReturn(list);
        when(mapper.toDtoList(list)).thenReturn(List.of(dto));

        ResponseDto response = productService.findAllProducts();

        assertNotNull(response.getContent());
        assertEquals(0, response.getCode());
        verify(repository).findAll();
    }

    @Test
    void findAllProducts_WhenListIsEmpty_ShouldThrowBusinessException() {
        when(repository.findAll()).thenReturn(Collections.emptyList());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            productService.findAllProducts();
        });

        assertEquals(4, Integer.parseInt(exception.getCode()));
    }
}
