package vn.iotstar.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.iotstar.entity.*;
import vn.iotstar.repository.OtpTokenRepository;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service @RequiredArgsConstructor
public class OtpService {
    private final OtpTokenRepository tokens; private final MailService mail; private final SecureRandom random=new SecureRandom();
    @Value("${app.otp.expiry-minutes:10}") private long expiry;
    @Transactional public void issue(String email, OtpPurpose purpose) { tokens.deleteByEmailIgnoreCaseAndPurpose(email,purpose); String code=String.format("%06d",random.nextInt(1_000_000)); tokens.save(new OtpToken(email.toLowerCase(),code,purpose,LocalDateTime.now().plusMinutes(expiry))); mail.sendOtp(email,code,purpose==OtpPurpose.REGISTER?"đăng ký":"đặt lại mật khẩu"); }
    @Transactional public void verify(String email,String code,OtpPurpose purpose) { var token=tokens.findTopByEmailIgnoreCaseAndPurposeAndUsedFalseOrderByIdDesc(email,purpose).orElseThrow(()->new IllegalArgumentException("OTP không tồn tại hoặc đã hết hạn")); if(!token.isValid(code)) throw new IllegalArgumentException("OTP không đúng hoặc đã hết hạn"); token.setUsed(true); tokens.save(token); }
}
