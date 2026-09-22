package vn.iotstar.repository;
import vn.iotstar.entity.Product;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name,String description,Pageable pageable);
    Page<Product> findByUserId(Long userId,Pageable pageable);
    long countByUserId(Long userId);
}
