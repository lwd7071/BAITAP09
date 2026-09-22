package vn.iotstar.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Getter @Setter public class RegisterDTO { @Email @NotBlank private String email; @NotBlank private String fullName; @Size(min=6,max=100) private String password; @Size(min=6,max=100) private String confirmPassword; }
