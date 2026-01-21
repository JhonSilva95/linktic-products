package linktic.products.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodesAndDescriptionResponses {

    OK(0, "OK"),
    GENERAL_ERROR_PRODUCTS(1, "GENERAL ERROR IN PRODUCTS"),
    ERROR_SAVING_PRODUCT(2, "ERROR SAVING THE PRODUCT"),
    PRODUCT_NOT_FOUND(3, "PRODUCT NOT FOUND"),
    PRODUCTS_NOT_FOUNDS(4, "PRODUCTS NOT FOUNDS");

    private final Integer code;
    private final String description;
}
