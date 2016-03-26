package openu.ibdb.controllers;

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

import openu.ibdb.models.BookCategory;
import openu.ibdb.repositories.BookCategoryRepository;

@RestController
public class BookCategoryController {
  
  @RequestMapping("/book/categories")	
  public Iterable<BookCategory> categories() {
	  return this.bookCategoryRepository.findAll();
  }
    
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/book/category/", method = RequestMethod.POST)
  public ResponseEntity<Void> createBookCategory(@RequestBody BookCategory bookCategory) {
      if (bookCategoryRepository.findOne(bookCategory.getId()) != null) {
          return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }

      bookCategoryRepository.save(bookCategory);
      
      return new ResponseEntity<Void>( HttpStatus.OK); 
  }
  
//-------------------Retrieve Single Book  http://127.0.0.1:8080/book/1--------------------------------------------------------
  
  @RequestMapping(value = "/book/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookCategory> getBookCategory(@PathVariable("id") int id) {
      BookCategory bookCategory =  bookCategoryRepository.findOne(id);
      if (bookCategory == null) {
          System.out.println("book with id " + id + " not found");
          return new ResponseEntity<BookCategory>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<BookCategory>(bookCategory, HttpStatus.OK);
  }
  
//  @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
//  public ResponseEntity<BookCategory> deleteCategory(@PathVariable("id") int id) {
//      System.out.println("Deleting book with id " + id);
//
//      BookCategory book = bookCategoryRepository.findOne(id);
//      if (book == null) {
//          System.out.println("Unable to delete. Book with id " + id + " not found");
//          return new ResponseEntity<BookCategory>(HttpStatus.NOT_FOUND);
//      }
//
//      bookCategoryRepository.delete(id);
//      return new ResponseEntity<BookCategory>(HttpStatus.NO_CONTENT);
//  }
//	    
//   
//  //------------------- Delete All Books --------------------------------------------------------
//    
//  @RequestMapping(value = "/books/", method = RequestMethod.DELETE)
//  public ResponseEntity<Book> deleteAllBooks() {
//      System.out.println("Deleting All Users");
//
//      bookCategoryRepository.deleteAll();
//      return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
//  }
//  
//  
//  //------------------- Update a book --------------------------------------------------------
//  @ResponseBody
//  @ResponseStatus(HttpStatus.OK)
//  @RequestMapping(value = "/book/", method = RequestMethod.PUT)
//  public ResponseEntity<Void> updateUser(@RequestBody Book book) {
//      System.out.println("Updating User " + book.getBookId());
//        
//      Book myBook = bookCategoryRepository.findOne(book.getBookId());
//        
//      if (myBook==null) {
//          System.out.println("book with id " + book.getBookId() + " not found");
//          return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//      }
//      
//      myBook.setBookAbstract(book.getBookAbstract());
//
//      bookCategoryRepository.save(myBook);
//      return new ResponseEntity<Void>( HttpStatus.OK);
//  }
//  
  
   
  @Autowired BookCategoryRepository bookCategoryRepository;
}
