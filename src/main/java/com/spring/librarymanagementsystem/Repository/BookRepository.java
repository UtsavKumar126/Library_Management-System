package com.spring.librarymanagementsystem.Repository;

import com.spring.librarymanagementsystem.Entity.Book;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
}
