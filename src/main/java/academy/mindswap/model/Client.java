package academy.mindswap.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@ToString
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ToString.Exclude
    private String pin;
    @ManyToMany(mappedBy = "clients")
    @ToString.Exclude
    private List<Account> accounts;


    public Client() {
    }

}
