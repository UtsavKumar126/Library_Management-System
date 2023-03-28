package com.spring.librarymanagementsystem.Controller;

import com.spring.librarymanagementsystem.DTOs.BookAddDto;
import com.spring.librarymanagementsystem.Entity.Book;
import com.spring.librarymanagementsystem.Exception.AuthorNotFoundExeption;
import com.spring.librarymanagementsystem.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookAddDto bookAddDto) throws AuthorNotFoundExeption {
            try{
                bookService.addBook(bookAddDto);
            }
            catch (AuthorNotFoundExeption e){
               return e.getMessage();
            }
            return "Book added successfully";
    }

    @GetMapping("/find_all")
    public List<Book> findallBooks(){
        return bookService.findallBooks();
    }

    @DeleteMapping("/delete-book")
    public String deleteBook(@RequestParam("id") int bookId){
        return bookService.deleteBookById(bookId);
    }

}
