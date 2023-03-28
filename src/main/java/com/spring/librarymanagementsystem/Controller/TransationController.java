package com.spring.librarymanagementsystem.Controller;

import com.spring.librarymanagementsystem.DTOs.IssueBookReqDto;
import com.spring.librarymanagementsystem.DTOs.IssueBookRespDto;
import com.spring.librarymanagementsystem.Entity.Transation;
import com.spring.librarymanagementsystem.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Transaction")
public class TransationController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("issue")
    public ResponseEntity issueBook(@RequestBody IssueBookReqDto issueBookReqDto) throws Exception {

        IssueBookRespDto issueBookRespDto;
        try {
            issueBookRespDto = transactionService.issueBook(issueBookReqDto);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(issueBookRespDto,HttpStatus.ACCEPTED);
    }

    @PutMapping("return")
    public String returnBook(@RequestBody IssueBookReqDto issueBookReqDto) throws Exception {
        return transactionService.returnBook(issueBookReqDto);
    }

    @GetMapping("get_success_txns_card")
    public String findSuccessfull_txns(@RequestParam("card") int cardId){

        List<Transation> transationList=transactionService.findSuccessTxns(cardId);

        String ans="";

        for(Transation transation:transationList){
            ans+=transation.getTransctionNumber()+"\n";

        }

        return ans;
    }
}
