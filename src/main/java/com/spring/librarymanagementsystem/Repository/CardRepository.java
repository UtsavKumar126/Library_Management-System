package com.spring.librarymanagementsystem.Repository;

import com.spring.librarymanagementsystem.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
