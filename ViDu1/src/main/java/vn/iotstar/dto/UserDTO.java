package vn.iotstar.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserDTO { private Long id; @Email @NotBlank private String email; @NotBlank private String fullName; @NotNull private Long roleId; private String roleName; private boolean enabled; }
