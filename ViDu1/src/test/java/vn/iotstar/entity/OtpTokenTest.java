package vn.iotstar.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class OtpTokenTest {
    @Test void validCodeMustMatchAndBeUnexpired() {
        var token = new OtpToken("a@example.com", "123456", OtpPurpose.REGISTER, LocalDateTime.now().plusMinutes(1));
        assertTrue(token.isValid("123456"));
        assertFalse(token.isValid("999999"));
    }
    @Test void expiredOrUsedCodeIsInvalid() {
        var expired = new OtpToken("a@example.com", "123456", OtpPurpose.REGISTER, LocalDateTime.now().minusSeconds(1));
        assertFalse(expired.isValid("123456"));
        expired.setExpiresAt(LocalDateTime.now().plusMinutes(1)); expired.setUsed(true);
        assertFalse(expired.isValid("123456"));
    }
}
