package com.spring.librarymanagementsystem.Service;

import com.spring.librarymanagementsystem.DTOs.IssueBookReqDto;
import com.spring.librarymanagementsystem.DTOs.IssueBookRespDto;
import com.spring.librarymanagementsystem.Entity.Book;
import com.spring.librarymanagementsystem.Entity.Card;
import com.spring.librarymanagementsystem.Entity.Transation;
import com.spring.librarymanagementsystem.Enums.CardStatus;
import com.spring.librarymanagementsystem.Enums.TransactionStatus;
import com.spring.librarymanagementsystem.Repository.BookRepository;
import com.spring.librarymanagementsystem.Repository.CardRepository;
import com.spring.librarymanagementsystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    private JavaMailSender emailSender;
    public IssueBookRespDto issueBook(IssueBookReqDto issueBookReqDto) throws Exception {
        Transation transation=new Transation();
        transation.setTransctionNumber(String.valueOf(UUID.randomUUID()));
        transation.setIssueOperation(true);

        Book book;
        try{
            book=bookRepository.findById(issueBookReqDto.getBookId()).get();
        }
        catch (Exception e){
            transation.setTransactionStatus(TransactionStatus.FAILED);
            transation.setTranactionMessage("Book not found");
            transactionRepository.save(transation);
            throw new Exception("Book not found");
        }

        Card card;
        try{
            card=cardRepository.findById(issueBookReqDto.getCardId()).get();
        }
        catch (Exception e){
            transation.setTransactionStatus(TransactionStatus.FAILED);
            transation.setTranactionMessage("Card not found");
            transactionRepository.save(transation);
            throw new Exception("Card not found");
        }
        transation.setCard(card);
        transation.setBook(book);

        if(book.isIssued()){
            transation.setTransactionStatus(TransactionStatus.FAILED);
            transation.setTranactionMessage("Book not available");
            transactionRepository.save(transation);
            throw new Exception("Book not available");
        }
        if(card.getCardStatus()!= CardStatus.ACTIVATED){
            transation.setTransactionStatus(TransactionStatus.FAILED);
            transation.setTranactionMessage("Card  is not valid");
            transactionRepository.save(transation);
            throw new Exception("Card is in valid");
        }

        transation.setTransactionStatus(TransactionStatus.SUCCESS);
        transation.setTranactionMessage("Transaction successful");

        book.setIssued(true);
        book.setCard(card);
        book.getTransationList().add(transation);
        card.getTransactionList().add(transation);
        card.getBookIssued().add(book);

        cardRepository.save(card);

        String text="Congrats !! "+card.getStudent().getName()+" Book :- "+book.getTitle()+" has been issued with Transaction Id :- "
                +transation.getTransctionNumber();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ironmanavengersutsav@gmail.com");
        message.setTo(card.getStudent().getEmail());
        message.setSubject("Book Issue Notification");
        message.setText(text);
        emailSender.send(message);

        IssueBookRespDto issueBookRespDto=new IssueBookRespDto();
        issueBookRespDto.setTransActionId(transation.getTransctionNumber());
        issueBookRespDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookRespDto.setBookName(book.getTitle());

       return issueBookRespDto;
    }

    public String returnBook(IssueBookReqDto issueBookReqDto) throws Exception {
        Transation transation=new Transation();
        transation.setTransctionNumber(String.valueOf(UUID.randomUUID()));
        transation.setIssueOperation(false);

        Book book=bookRepository.findById(issueBookReqDto.getBookId()).get();

        Card card=cardRepository.findById(issueBookReqDto.getCardId()).get();

        transation.setCard(card);
        transation.setBook(book);

        transation.setTransactionStatus(TransactionStatus.SUCCESS);
        transation.setTranactionMessage("Transaction successful");

        book.setIssued(false);
        book.setCard(null);
        book.getTransationList().add(transation);
        card.getTransactionList().add(transation);
        card.getBookIssued().remove(book);

        cardRepository.save(card);

        return "Book return Successful";
    }

    public List<Transation> findSuccessTxns(int cardId) {
       return transactionRepository.findSuccessTxns(cardId);
    }
}
