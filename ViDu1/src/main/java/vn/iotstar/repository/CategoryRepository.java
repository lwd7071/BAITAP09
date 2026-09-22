package vn.iotstar.repository;
import vn.iotstar.entity.Category;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface CategoryRepository extends JpaRepository<Category,Long> { Optional<Category> findByNameIgnoreCase(String name); Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable); }
