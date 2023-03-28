package com.spring.librarymanagementsystem.Service;
import com.spring.librarymanagementsystem.DTOs.BookAddDto;
import com.spring.librarymanagementsystem.Entity.Author;
import com.spring.librarymanagementsystem.Entity.Book;
import com.spring.librarymanagementsystem.Exception.AuthorNotFoundExeption;
import com.spring.librarymanagementsystem.Repository.AuthorRepository;
import com.spring.librarymanagementsystem.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;


    public void addBook(BookAddDto bookAddDto) throws AuthorNotFoundExeption {
        Author author;
        try {
            author = authorRepository.findById(bookAddDto.getAuthorId()).get();
        }
        catch (Exception e){
            throw new AuthorNotFoundExeption("Author Does not Exist");
        }

        Book book=new Book();
        book.setTitle(bookAddDto.getTitle());
        book.setPrice(bookAddDto.getPrice());
        book.setGenre(bookAddDto.getGenre());

        book.setAuthor(author);

        List<Book>booksWritten=author.getBookWritten();
        booksWritten.add(book);

        authorRepository.save(author);
    }

    public List<Book> findallBooks() {

        return bookRepository.findAll();
    }
    public String deleteBookById(int id){
        bookRepository.deleteById(id);

        return "Book deleted successfully";
    }
}
