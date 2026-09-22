package vn.iotstar.repository;
import vn.iotstar.entity.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface OtpTokenRepository extends JpaRepository<OtpToken,Long> {
    Optional<OtpToken> findTopByEmailIgnoreCaseAndPurposeAndUsedFalseOrderByIdDesc(String email, OtpPurpose purpose);
    void deleteByEmailIgnoreCaseAndPurpose(String email, OtpPurpose purpose);
}
