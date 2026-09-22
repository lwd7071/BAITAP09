package vn.iotstar.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.iotstar.entity.Category; import vn.iotstar.entity.Product; import vn.iotstar.entity.User; import vn.iotstar.entity.Role; import vn.iotstar.repository.*;
@Configuration @RequiredArgsConstructor
public class DataInitializer {
    @Bean CommandLineRunner init(RoleRepository roles,UserRepository users,CategoryRepository cats,ProductRepository products,PasswordEncoder encoder,@Value("${ADMIN_EMAIL:admin@example.com}") String email,@Value("${ADMIN_PASSWORD:ChangeMe123!}") String password) { return args -> {
        Role user=roles.findByNameIgnoreCase("USER").orElseGet(()->roles.save(new Role("USER"))); Role admin=roles.findByNameIgnoreCase("ADMIN").orElseGet(()->roles.save(new Role("ADMIN")));
        if(!users.existsByEmailIgnoreCase(email)){ User u=new User(); u.setEmail(email.toLowerCase()); u.setFullName("System Administrator"); u.setPassword(encoder.encode(password)); u.setRole(admin); u.setEnabled(true); users.save(u); }
        Category demo=cats.findByNameIgnoreCase("Mặc định").orElseGet(()->cats.save(new Category("Mặc định")));
        if(products.count()==0){ Product p=new Product(); p.setName("Sản phẩm mẫu"); p.setPrice(java.math.BigDecimal.valueOf(100000)); p.setDescription("Dữ liệu mẫu của Ví dụ 1"); p.setCategory(demo); p.setUser(users.findByEmailIgnoreCase(email).orElseThrow()); products.save(p); }
    }; }
}
