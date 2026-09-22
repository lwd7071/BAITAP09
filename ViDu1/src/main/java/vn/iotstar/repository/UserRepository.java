package vn.iotstar.repository;
import vn.iotstar.entity.User;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import java.util.*;
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCase(String email);
    Page<User> findByEmailContainingIgnoreCaseOrFullNameContainingIgnoreCase(String email,String fullName,Pageable pageable);
    @Query("select u from User u join fetch u.role where lower(u.email)=lower(:email)") Optional<User> findByEmailWithRole(String email);
}
