package vn.iotstar.dto;
import jakarta.validation.constraints.Size;
import lombok.*;
@Getter @Setter public class ResetPasswordDTO { @Size(min=6,max=100) private String password; @Size(min=6,max=100) private String confirmPassword; }
