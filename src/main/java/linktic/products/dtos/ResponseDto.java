package linktic.products.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    private Integer code;
    private String description;
}
