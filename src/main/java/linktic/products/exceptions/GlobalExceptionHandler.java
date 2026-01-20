package linktic.products.exceptions;

import linktic.products.dtos.ResponseDto;
import linktic.products.utils.CodesAndDescriptionResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> handleBusinessException(DataAccessException ex) {
        log.error("Error saving product: {}", ex.getMessage());
        ResponseDto error = ResponseDto.builder()
                .code(CodesAndDescriptionResponses.ERROR_SAVING_PRODUCT.getCode())
                .description(CodesAndDescriptionResponses.ERROR_SAVING_PRODUCT.getDescription())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGenericException(Exception ex) {
        log.error("General Exception: {}", ex.getMessage());
        ResponseDto error = ResponseDto.builder()
                .code(CodesAndDescriptionResponses.GENERAL_ERROR_PRODUCTS.getCode())
                .description(CodesAndDescriptionResponses.GENERAL_ERROR_PRODUCTS.getDescription())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
