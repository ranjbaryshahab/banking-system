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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARD_SEQ")
    @GenericGenerator(
            name = "CARD_SEQ",
            strategy = "ir.maktab.java32.project.spring.bankingsystem.utils.DatePrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = DatePrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d"),
                    @Parameter(name = DatePrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "6037")
            })
    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @OneToOne
    @JsonIgnore
    private Account account;

    @Embedded
    private CardPasswordInfo cardPasswordInfo;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
