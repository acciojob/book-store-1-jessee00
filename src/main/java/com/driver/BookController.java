package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> listOfBooks;
    private int id;

    public List<Book> getBookList() {
        return listOfBooks;
    }

    public void setBookList(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.listOfBooks = new ArrayList<Book>();
        this.id = 1;
    }


    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        book.setId(id);
        this.id++;
        listOfBooks.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") String id)
    {
        Book ret = null;
        for(Book b : listOfBooks)
        {
            if(Integer.parseInt(id) == b.getId()) ret =b;
        }
        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }


    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return new ResponseEntity<>(listOfBooks,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author)
    {
        List<Book> ret = new ArrayList<>();
        for(Book b : listOfBooks)
        {
            if(b.getAuthor().equalsIgnoreCase(author)) ret.add(b);
        }

        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre)
    {
        List<Book> ret = new ArrayList<>();
        for(Book b : listOfBooks)
        {
            if(b.getGenre().equalsIgnoreCase(genre)) ret.add(b);
        }
        return new ResponseEntity<>(ret, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") String id)
    {
        for(Book b : listOfBooks)
        {
            if(Integer.parseInt(id) == b.getId())
            {
                listOfBooks.remove(b);
                break;
            }
        }
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks()
    {
        listOfBooks = new ArrayList<>();
        return new ResponseEntity<>("success", HttpStatus.ACCEPTED);
    }
}
