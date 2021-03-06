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

/**
 * This class responsible for book category web services actions
 * @author gulevy
 *
 */
@RestController
public class BookCategoryController {
  
  @RequestMapping("/book/categories")	
  public Iterable<BookCategory> categories() {
	  return this.bookCategoryRepository.findAll();
  }
    
  
  /**
   * Create new book category
   * @param bookCategory - a book category data
   * @return
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/book/category/", method = RequestMethod.POST)
  public ResponseEntity<Void> createBookCategory(@RequestBody BookCategory bookCategory) {
      //find book category by id
	  if (bookCategoryRepository.findOne(bookCategory.getId()) != null) {
          return new ResponseEntity<Void>(HttpStatus.CONFLICT);
      }

      bookCategoryRepository.save(bookCategory);
      
      return new ResponseEntity<Void>( HttpStatus.OK); 
  }

  /**
   * Retrieve Single Book category by id.
   * @param id - book category id
   * @return
   */
  @RequestMapping(value = "/book/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<BookCategory> getBookCategory(@PathVariable("id") int id) {
      BookCategory bookCategory =  bookCategoryRepository.findOne(id);
      if (bookCategory == null) {
          System.out.println("book with id " + id + " not found");
          return new ResponseEntity<BookCategory>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<BookCategory>(bookCategory, HttpStatus.OK);
  }
     
  @Autowired BookCategoryRepository bookCategoryRepository;
}
