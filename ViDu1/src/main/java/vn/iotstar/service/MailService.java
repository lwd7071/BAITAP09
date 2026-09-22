package vn.iotstar.service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service @RequiredArgsConstructor
public class MailService {
    private final JavaMailSender sender;
    @Value("${app.mail.from:no-reply@example.com}") private String from;
    public void sendOtp(String to, String code, String purpose) { var m=new SimpleMailMessage(); m.setFrom(from); m.setTo(to); m.setSubject("Mã xác nhận - Ví dụ 1"); m.setText("Mã OTP cho "+purpose+" của bạn là: "+code+"\nMã có hiệu lực trong thời gian ngắn."); sender.send(m); }
}
