package ir.maktab.java32.project.spring.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ROLE")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ROLE_TYPE" ,discriminatorType = DiscriminatorType.STRING)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Person person;
}
