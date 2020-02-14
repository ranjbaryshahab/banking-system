package ir.maktab.java32.project.spring.bankingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    @Column(name = "STREET")
    private String street;

    @Column(name = "ALLEY")
    private String alley;

    @Column(name = "HOUSE_NUMBER")
    private String houseNumber;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

}
