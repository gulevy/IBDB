package openu.ibdb.controllers;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import openu.ibdb.models.Proposal;
import openu.ibdb.models.ProposalState.Status;
import openu.ibdb.models.ResultData;
import openu.ibdb.repositories.AuthorRepository;
import openu.ibdb.repositories.BookRepository;
import openu.ibdb.repositories.ProposalRepository;


/**
 * This class responsible for author web services actions
 */
@RestController
public class AuthorController {

	/**
	 *  Annotation for mapping web requests onto specific handler classes and/or
	 *  handler methods
	 */
	@RequestMapping("/authors")
	public Iterable<Author> authors() {
		return this.authorRepository.findAll();
	}
	
	//return only approve authors
	@RequestMapping("/authors/approved")
	public Iterable<Author> approveAuthors() {

		List<Author> authors = new ArrayList<>();	
		Set<Author> author = new HashSet<Author>();
		Collection<Proposal> proposals =  this.proposalRepository.findAllByStateHistoryProposalStatusOrderByStateHistoryStateIdAsc(Status.approved);
		
		for (Proposal proposal : proposals) {
			author.add(proposal.getBook().getAuthor());
		}
	
		return author;
	}
	
    /**
     *  Create new Author in db 
     * @param author - the author to create
     * @return
     */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/author/", method = RequestMethod.POST)
	public ResponseEntity<String> createAuthor(@RequestBody Author author) {
		ResultData res ;
		System.out.println("Creating author " + author.getFirstName());

		//check if author doenot exist
		if (authorRepository.findOne(author.getAuthorId()) != null) {
			 res = new ResultData(false, "A Author with id " + author.getAuthorId() + " already exist");
	         return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		//add the new author
		authorRepository.save(author);
		res = new ResultData(true, "Author was added successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	/**
	 * Delete specific author by id
	 * 
	 * rest example :  delete author by id http delete -> http://127.0.0.1:8080/author/1
	 * @param id  - author id 
	 * @return
	 */
	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") int id) {
		ResultData res ;
		
		//search for author to delete
		Author author = authorRepository.findOne(id);
		if (author == null) {	
			res = new ResultData(false, "Unable to delete. Author with id " + id + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		//find all books related to author
		Collection<Book> books = bookRepository.findByAuthor(author);
		
		if (books.size() > 0) {
			System.out.println("Cannot delete author that contains books");
			res = new ResultData(false, "Unable to delete. Author id " + id + " author contains books");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		
		//delete author only if it doesnot have any books related
		System.out.println("Deleting author with id " + id);
		authorRepository.delete(id);
		res = new ResultData(true, "Author id " + id + " was deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	/**
	 * Delete All authors.
	 * not in use
	 * @return
	 */
	@RequestMapping(value = "/authors/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllAuthors() {
		ResultData res ;
		System.out.println("Deleting All Authors");

		authorRepository.deleteAll();
		res = new ResultData(true, "All authors were deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}
	
	/**
	 * Update a Author . 
	 * will search a author with the same id and update it according to the new object
	 * @param author - update author element 
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/author/", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
		ResultData res ;
		System.out.println("Updating Author " + author.getAuthorId());

		//find author by id
		Author myAuthor = authorRepository.findOne(author.getAuthorId());

		if (myAuthor == null) {
			res = new ResultData(false, "Author with id " + author.getAuthorId() + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		//update author properties
		myAuthor.setFirstName(author.getFirstName());
		myAuthor.setLastName(author.getLastName());
		myAuthor.setDateOfBirth(author.getDateOfBirth());
		myAuthor.setLinkWiki(author.getLinkWiki());
		
		authorRepository.save(myAuthor);
		
		res = new ResultData(true, "Author id "  + author.getAuthorId() +" were update successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	/**
	 * Retrieve Specific author by id
	 * 
	 * http get -> http://127.0.0.1:8080/author/1
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthor(@PathVariable("id") int id) {
		System.out.println("Fetching Author with id " + id);
		Author author = authorRepository.findOne(id);
		if (author == null) {
			System.out.println("author with id " + id + " not found");
			return new ResponseEntity<Author>(HttpStatus.OK);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ProposalRepository proposalRepository;
}
