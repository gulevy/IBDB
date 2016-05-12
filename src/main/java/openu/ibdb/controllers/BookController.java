package openu.ibdb.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import openu.ibdb.models.Author;
import openu.ibdb.models.Book;
import openu.ibdb.models.BookCategory;
import openu.ibdb.models.ResultData;
import openu.ibdb.repositories.AuthorRepository;
import openu.ibdb.repositories.BookCategoryRepository;
import openu.ibdb.repositories.BookRepository;

@RestController
public class BookController {
  
  @RequestMapping("/books")	
  public Iterable<Book> books() {
	  Iterable<Book> books = this.bookRepository.findAll();
	  for (Book book : books) {
		    
		if (!new File(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\assets\\images\\books\\" + book.getImageName()).exists()) {
			book.setImageName("book_default.png");
		}
	  }
	  
	  return books;
  }
   
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/book/", method = RequestMethod.POST)
  public ResponseEntity<String> createBook(@RequestBody Book book) {
	  ResultData res ;
      System.out.println("Creating book " + book.getName());

      if (bookRepository.findOne(book.getBookId()) != null) {
          System.out.println("A book with name " + book.getName() + " already exist");
          
          res = new ResultData(false, "A book with name " + book.getName() + " already exist");
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }

      bookRepository.save(book);
      
      res = new ResultData(true, "Book was added successful");
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
  }
  
  //delete book by id  http://127.0.0.1:8080/book/1
  @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteBook(@PathVariable("id") int id) {
	  ResultData res ;
      System.out.println("Deleting book with id " + id);

      Book book = bookRepository.findOne(id);
      if (book == null) { 
          res = new ResultData(false, "Unable to delete. Book with id " + id + " not found");
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }

      bookRepository.delete(id);
      
      res = new ResultData(true , "Book id " + id + " was deleted successfully");
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
  }
	    
   
  //------------------- Delete All Books --------------------------------------------------------
    
  @RequestMapping(value = "/books/", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteAllBooks() {
	  ResultData res ;
      System.out.println("Deleting All Users");

      bookRepository.deleteAll();
      res = new ResultData(true , "Delete all books successfully");
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
  }
  
  
  //------------------- Update a book --------------------------------------------------------
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/book/", method = RequestMethod.PUT)
  public ResponseEntity<String> updateBook(@RequestBody Book book) {
	  ResultData res ;
      System.out.println("Updating Book " + book.getBookId());
        
      Book myBook = bookRepository.findOne(book.getBookId());
        
      if (myBook==null) {
          res = new ResultData(false , "Book " + book.getName() + " not found");
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }
      
      myBook.setBookAbstract(book.getBookAbstract());
      myBook.getAuthor().setAuthorId(book.getAuthor().getAuthorId());
      
      //check if author value was change
      if (myBook.getAuthor().getAuthorId()!=book.getAuthor().getAuthorId()) {
    	  Author myAuthor = authorRepository.findOne(book.getAuthor().getAuthorId());  
    	  myBook.setAuthor(myAuthor);
      }
      
      //check if vategory value
      if (myBook.getBookCategory().getId()!=book.getBookCategory().getId()) {
    	  BookCategory bc = bcRepository.findOne(book.getBookCategory().getId());  
    	  myBook.setBookCategory(bc);
      }

      myBook.setImageName(book.getImageName());
      myBook.setName(book.getName());
      myBook.setPublisherName(book.getPublisherName());
      myBook.setReleaseDate(book.getReleaseDate());
      
      bookRepository.save(myBook);
      
      res = new ResultData(true , "Book " + book.getName() + " was update successfully");
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
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
  
  @Autowired AuthorRepository  authorRepository;
  @Autowired BookCategoryRepository bcRepository;
  @Autowired BookRepository bookRepository;
}
