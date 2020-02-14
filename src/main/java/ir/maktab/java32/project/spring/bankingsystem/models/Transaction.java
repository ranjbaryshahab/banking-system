package ir.maktab.java32.project.spring.bankingsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.maktab.java32.project.spring.bankingsystem.enumuration.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    private Date date;

    @Column(name = "TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "BALANCE")
    private Long balance;

    @ManyToOne
    @JsonIgnore
    private Card from;

    @ManyToOne
    @JsonIgnore
    private Card to;

    @Column(name = "SUCCESSFUL")
    private Boolean isSuccessful;

}


