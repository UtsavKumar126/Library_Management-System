package com.spring.librarymanagementsystem.Repository;

import com.spring.librarymanagementsystem.Entity.Transation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transation,Integer> {


    @Query(value = "Select * from transation t where t.card_Id=:cardId and t.transaction_status='SUCCESS'",nativeQuery = true)
    List<Transation> findSuccessTxns(int cardId);
}
