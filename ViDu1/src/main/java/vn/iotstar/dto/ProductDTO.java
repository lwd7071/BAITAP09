package vn.iotstar.dto;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductDTO { private Long id; @NotBlank private String name; @NotNull @Positive private BigDecimal price; private String description; private String imageUrl; @NotNull private Long categoryId; private String categoryName; }
