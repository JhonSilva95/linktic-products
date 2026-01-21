package linktic.products.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor  // <--- ESTE ES EL QUE FALTA PARA JACKSON
@AllArgsConstructor
public class ProductDto {
    private Integer id;
    private String name;
    private Long price;
    private String description;
}
