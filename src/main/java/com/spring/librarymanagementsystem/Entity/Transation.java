package com.spring.librarymanagementsystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.librarymanagementsystem.Enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String transctionNumber;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @CreationTimestamp
    private Date transactionDate;
    private boolean isIssueOperation;
    private String tranactionMessage;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Card card;


    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Book book;


}
