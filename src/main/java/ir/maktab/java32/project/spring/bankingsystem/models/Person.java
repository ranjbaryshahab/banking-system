package ir.maktab.java32.project.spring.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "NATIONAL_CODE", nullable = false, unique = true)
    private String nationalCode;

    @Column(name = "FATHERS_NAME")
    private String fathersName;

    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @JsonIgnore
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private Set<Role> roles;
}
