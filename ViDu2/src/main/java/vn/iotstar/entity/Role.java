package vn.iotstar.entity; import jakarta.persistence.*; import lombok.*;
@Entity @Table(name="roles") @Getter @Setter @NoArgsConstructor @AllArgsConstructor public class Role { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id; @Column(nullable=false,unique=true) String name; public Role(String n){name=n;} }
