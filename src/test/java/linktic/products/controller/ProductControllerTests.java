package linktic.products.controller;

import linktic.products.dtos.ProductDto;
import linktic.products.dtos.ResponseDto;
import linktic.products.service.IProductService;
import linktic.products.utils.CodesAndDescriptionResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ResponseDto successResponse;
    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = ProductDto.builder()
                .name("Laptop")
                .price(1500000L)
                .description("Gaming Laptop")
                .build();

        successResponse = ResponseDto.builder()
                .code(CodesAndDescriptionResponses.OK.getCode())
                .description(CodesAndDescriptionResponses.OK.getDescription())
                .content(productDto)
                .build();
    }

    @Test
    void createProduct_ShouldReturnOk() throws Exception {
        when(productService.createProduct(any(ProductDto.class))).thenReturn(successResponse);

        mockMvc.perform(post("/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(CodesAndDescriptionResponses.OK.getCode()))
                .andExpect(jsonPath("$.description").value(CodesAndDescriptionResponses.OK.getDescription()));
    }

    @Test
    void findProductById_ShouldReturnProduct() throws Exception {
        when(productService.findProductById(1)).thenReturn(successResponse);

        mockMvc.perform(get("/products/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(CodesAndDescriptionResponses.OK.getCode()))
                .andExpect(jsonPath("$.content.name").value("Laptop"));
    }

    @Test
    void findAllProducts_ShouldReturnList() throws Exception {
        ResponseDto listResponse = ResponseDto.builder()
                .code(CodesAndDescriptionResponses.OK.getCode())
                .description(CodesAndDescriptionResponses.OK.getDescription())
                .content(List.of(productDto))
                .build();

        when(productService.findAllProducts()).thenReturn(listResponse);

        mockMvc.perform(get("/products/find-all-products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content[0].name").value("Laptop"));
    }
}
