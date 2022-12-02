package com.driver;
import java.util.*;


import org.springframework.stereotype.Repository;

@Repository
public class BookRepository
{

    private int count;
    private List<Book> listOfBooks;

    public BookRepository(){
        this.listOfBooks = new ArrayList<>();
        this.count = 1;
    }

    public Book save(Book book)
    {
        book.setId(count);
        count++;
        listOfBooks.add(book);
        return book;
    }

    public Book findBookById(int id)
    {
        for(Book b : listOfBooks)
        {
            if(b.getId() == id) return b;
        }
        return null;
    }

    public List<Book> findAll()
    {
        return listOfBooks;
    }

    public void deleteBookById(int id){
        for(Book b : listOfBooks)
        {
            if(b.getId() == id)
            {
                listOfBooks.remove(b);
                return;
            }
        }
    }

    public void deleteAll()
    {
        listOfBooks = new ArrayList<>();
    }

    public List<Book> findBooksByAuthor(String author)
    {
        List<Book> ret = new ArrayList<>();
        for(Book b : listOfBooks)
        {
            if(b.getAuthor().equalsIgnoreCase(author)) ret.add(b);
        }
        return ret;
    }

    public List<Book> findBooksByGenre(String genre)
    {
        List<Book> ret = new ArrayList<>();
        for(Book b : listOfBooks)
        {
            if(b.getGenre().equalsIgnoreCase(genre)) ret.add(b);
        }
        return ret;
    }
}
