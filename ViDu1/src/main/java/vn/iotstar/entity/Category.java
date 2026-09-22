package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity @Table(name="categories") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Category {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=100) private String name;
    @OneToMany(mappedBy="category") private List<Product> products = new ArrayList<>();
    public Category(String name) { this.name=name; }
}
