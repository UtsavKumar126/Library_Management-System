package com.spring.librarymanagementsystem.DTOs;

import com.spring.librarymanagementsystem.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookRespDto {

    private String transActionId;
    private String bookName;
    private TransactionStatus transactionStatus;

}
