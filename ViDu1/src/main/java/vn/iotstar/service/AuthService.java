package vn.iotstar.service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.dto.*; import vn.iotstar.entity.*; import vn.iotstar.repository.*;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository users; private final RoleRepository roles; private final PasswordEncoder encoder; private final OtpService otp;
    @Transactional public void register(RegisterDTO dto) { if(!dto.getPassword().equals(dto.getConfirmPassword())) throw new IllegalArgumentException("Mật khẩu xác nhận không khớp"); if(users.existsByEmailIgnoreCase(dto.getEmail())) throw new IllegalArgumentException("Email đã được sử dụng"); User u=new User(); u.setEmail(dto.getEmail().trim().toLowerCase()); u.setFullName(dto.getFullName().trim()); u.setPassword(encoder.encode(dto.getPassword())); u.setEnabled(false); u.setRole(roles.findByNameIgnoreCase("USER").orElseThrow()); users.save(u); otp.issue(u.getEmail(),OtpPurpose.REGISTER); }
    @Transactional public void verifyRegister(String email,String code) { otp.verify(email,code,OtpPurpose.REGISTER); User u=users.findByEmailIgnoreCase(email).orElseThrow(); u.setEnabled(true); users.save(u); }
    public void resendRegister(String email) { if(users.findByEmailIgnoreCase(email).filter(u->!u.isEnabled()).isEmpty()) throw new IllegalArgumentException("Tài khoản không cần xác nhận"); otp.issue(email,OtpPurpose.REGISTER); }
    public void requestReset(String email) { users.findByEmailIgnoreCase(email).ifPresent(u-> { if(u.isEnabled()) otp.issue(email,OtpPurpose.RESET_PASSWORD); }); }
    @Transactional public void resetPassword(String email,String code,ResetPasswordDTO dto) { if(!dto.getPassword().equals(dto.getConfirmPassword())) throw new IllegalArgumentException("Mật khẩu xác nhận không khớp"); otp.verify(email,code,OtpPurpose.RESET_PASSWORD); User u=users.findByEmailIgnoreCase(email).orElseThrow(); u.setPassword(encoder.encode(dto.getPassword())); users.save(u); }
}
