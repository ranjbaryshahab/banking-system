package ir.maktab.java32.project.spring.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BRANCH")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(mappedBy = "branch")
    @JsonIgnore
    private Boss boss;

    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<Employee> employees;


    @OneToMany(mappedBy = "branch")
    @JsonIgnore
    private Set<Account> accounts;

}
