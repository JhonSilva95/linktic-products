package linktic.products.exceptions;

import linktic.products.controller.ProductController;
import linktic.products.service.IProductService;
import linktic.products.utils.CodesAndDescriptionResponses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class GlobalExceptionHandlerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Test
    void handleBusinessException_ShouldReturnNotFound() throws Exception {
        Integer errorCode = CodesAndDescriptionResponses.PRODUCT_NOT_FOUND.getCode();
        String errorMsg = CodesAndDescriptionResponses.PRODUCT_NOT_FOUND.getDescription();
        when(productService.findProductById(1))
                .thenThrow(new BusinessException(errorMsg, errorCode));

        mockMvc.perform(get("/products/product/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(3))
                .andExpect(jsonPath("$.description").value(errorMsg))
                .andExpect(jsonPath("$.content").doesNotExist());
    }

    @Test
    void handleGenericException_ShouldReturnInternalError() throws Exception {
        when(productService.findAllProducts())
                .thenThrow(new RuntimeException("Error fatal de sistema"));

        mockMvc.perform(get("/products/find-all-products"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value(CodesAndDescriptionResponses.GENERAL_ERROR_PRODUCTS.getCode()))
                .andExpect(jsonPath("$.description").value(CodesAndDescriptionResponses.GENERAL_ERROR_PRODUCTS.getDescription()));
    }
}
