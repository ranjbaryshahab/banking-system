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
@Table(name = "CONTACT_INFO")
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "CELL_PHONE_NUMBER")
    private String cellPhoneNumber;

    @Embedded
    private Address address;

    @OneToOne
    @JsonIgnore
    private Person person;
}
