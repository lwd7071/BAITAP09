package vn.iotstar.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.*;
import java.util.*;

@Entity @Table(name="users") @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false, unique=true, length=120) private String email;
    @Column(nullable=false, length=150) private String password;
    @Column(nullable=false, length=120) private String fullName;
    @Column(nullable=false) private boolean enabled;
    @Column(nullable=false) private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="role_id") private Role role;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true) private List<Product> products = new ArrayList<>();
}
