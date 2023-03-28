package com.spring.librarymanagementsystem.Controller;

import com.spring.librarymanagementsystem.Entity.Author;
import com.spring.librarymanagementsystem.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        return authorService.addAuthor(author);
    }

    @GetMapping("/all_authors")
    public List<Author> getAllAuthors(){
        return authorService.getAll();
    }


    @GetMapping("/findByid")
    public Author findbyId(@RequestParam("id") int bookId){
        return authorService.findAuthorById(bookId);
    }
}
