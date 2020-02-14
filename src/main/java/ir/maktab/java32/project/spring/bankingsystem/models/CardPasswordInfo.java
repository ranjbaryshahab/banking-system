package ir.maktab.java32.project.spring.bankingsystem.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class CardPasswordInfo {

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CVV2")
    private String cvv2;

    @Column(name = "SECOND_PASSWORD")
    private String secondPassword;

    @Column(name = "EXPIRE_DATE")
    @Temporal(TemporalType.DATE)
    private Date expireDate;
}
