package vn.iotstar.dto; import jakarta.validation.constraints.Size; import lombok.*; @Getter @Setter public class ResetPasswordDTO{@Size(min=6) String password; @Size(min=6) String confirmPassword;}
