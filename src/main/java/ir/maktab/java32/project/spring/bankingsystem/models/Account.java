package ir.maktab.java32.project.spring.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.maktab.java32.project.spring.bankingsystem.utils.DatePrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_SEQ")
    @GenericGenerator(
            name = "ACCOUNT_SEQ",
            strategy = "ir.maktab.java32.project.spring.bankingsystem.utils.DatePrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "10")
            })
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    private Card card;

    @ManyToOne
    @JsonIgnore
    private Customer owner;

    @ManyToOne
    @JsonIgnore
    private Branch branch;

    @Column(name = "BALANCE")
    private Long balance;

    @OneToMany(mappedBy = "from")
    @JsonIgnore
    private Set<Transaction> transactions;

    @Temporal(TemporalType.DATE)
    @Column(name = "Account_Registration_Date")
    private Date accountRegistrationDate = new Date();
}
