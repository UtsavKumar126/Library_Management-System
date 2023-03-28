package com.spring.librarymanagementsystem.Service;

import com.spring.librarymanagementsystem.Entity.Author;
import com.spring.librarymanagementsystem.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    public String addAuthor(Author author) {
        authorRepository.save(author);

        return "Added successfully";
    }

    public List<Author> getAll() {

        return authorRepository.findAll();
    }

    public Author findAuthorById(int id){
        return authorRepository.findById(id).get();
    }
}
