package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;

@Entity @Table(name="otp_tokens") @Getter @Setter @NoArgsConstructor
public class OtpToken {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=120) private String email;
    @Column(nullable=false, length=10) private String code;
    @Enumerated(EnumType.STRING) @Column(nullable=false, length=30) private OtpPurpose purpose;
    @Column(nullable=false) private LocalDateTime expiresAt;
    @Column(nullable=false) private boolean used;
    public OtpToken(String email, String code, OtpPurpose purpose, LocalDateTime expiresAt) { this.email=email; this.code=code; this.purpose=purpose; this.expiresAt=expiresAt; }
    public boolean isValid(String candidate) { return !used && expiresAt.isAfter(LocalDateTime.now()) && code.equals(candidate); }
}
