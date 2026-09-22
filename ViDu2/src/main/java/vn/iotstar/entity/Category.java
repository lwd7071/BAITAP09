package vn.iotstar.entity; import jakarta.persistence.*; import lombok.*; import java.util.*;
@Entity @Getter @Setter @NoArgsConstructor public class Category { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false,unique=true) String name; @OneToMany(mappedBy="category") List<Product> products=new ArrayList<>(); public Category(String n){name=n;} }
