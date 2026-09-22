package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.*;
import java.time.*;

@Entity @Table(name="products") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, length=160) private String name;
    @Column(nullable=false, precision=15, scale=2) private BigDecimal price;
    @Column(length=1000) private String description;
    @Column(length=1000) private String imageUrl;
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="user_id") private User user;
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="category_id") private Category category;
}
