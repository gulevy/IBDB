package openu.ibdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import openu.ibdb.models.Book;
import openu.ibdb.repositories.BookRepository;

@RestController
public class BookController {
  
  @RequestMapping("/books")	
  public Iterable<Book> books() {
	  return this.bookRepository.findAll();
  }
  
  @RequestMapping(value = "/book/", method = RequestMethod.POST)
  public ResponseEntity<Void> createBook(@RequestBody Book book,    UriComponentsBuilder ucBuilder) {
      System.out.println("Creating book " + book.getName());

      if (bookRepository.findOne(book.getBookId()) != null) {
          System.out.println("A book with name " + book.getName() + " already exist");
          return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }

      bookRepository.save(book);

      HttpHeaders headers = new HttpHeaders();
      headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getBookId()).toUri());
      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }
  

  //delete book by id  http://127.0.0.1:8080/book/1
  @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Book> deleteUser(@PathVariable("id") int id) {
      System.out.println("Deleting book with id " + id);

      Book book = bookRepository.findOne(id);
      if (book == null) {
          System.out.println("Unable to delete. Book with id " + id + " not found");
          return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
      }

      bookRepository.delete(id);
      return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
  }
	    
   
  //------------------- Delete All Books --------------------------------------------------------
    
  @RequestMapping(value = "/books/", method = RequestMethod.DELETE)
  public ResponseEntity<Book> deleteAllBooks() {
      System.out.println("Deleting All Users");

      bookRepository.deleteAll();
      return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
  }
  
  
  //------------------- Update a book --------------------------------------------------------
  
  @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Book> updateUser(@PathVariable("id") int id, @RequestBody Book book) {
      System.out.println("Updating User " + id);
        
      Book myBook = bookRepository.findOne(id);
        
      if (myBook==null) {
          System.out.println("book with id " + id + " not found");
          return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
      }

      myBook.setName(book.getName());
    
      bookRepository.save(book);
      
      return new ResponseEntity<Book>(book, HttpStatus.OK);
  }
  
  
  
//-------------------Retrieve Single Book  http://127.0.0.1:8080/book/1--------------------------------------------------------
  
  @RequestMapping(value = "/book/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
      System.out.println("Fetching Book with id " + id);
      Book book =  bookRepository.findOne(id);
      if (book == null) {
          System.out.println("book with id " + id + " not found");
          return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  
  @Autowired BookRepository bookRepository;
}
