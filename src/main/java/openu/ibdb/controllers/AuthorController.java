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

import com.google.gson.Gson;

import openu.ibdb.models.Author;
import openu.ibdb.models.ResultData;
import openu.ibdb.repositories.AuthorRepository;

@RestController
public class AuthorController {

	@RequestMapping("/authors")
	public Iterable<Author> authors() {
		return this.authorRepository.findAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/author/", method = RequestMethod.POST)

	public ResponseEntity<String> createAuthor(@RequestBody Author author) {
		ResultData res ;
		System.out.println("Creating author " + author.getFirstName());

		if (authorRepository.findOne(author.getAuthorId()) != null) {
			 res = new ResultData(false, "A Author with id " + author.getAuthorId() + " already exist");
	         return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		authorRepository.save(author);
		res = new ResultData(true, "Author was added successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// delete author by id http://127.0.0.1:8080/author/1
	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAuthor(@PathVariable("id") int id) {
		ResultData res ;
		System.out.println("Deleting author with id " + id);

		Author author = authorRepository.findOne(id);
		if (author == null) {	
			res = new ResultData(false, "Unable to delete. Author with id " + id + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		authorRepository.delete(id);
		res = new ResultData(true, "Author id " + id + " was deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// ------------------- Delete All authors
	// --------------------------------------------------------

	@RequestMapping(value = "/authors/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllAuthors() {
		ResultData res ;
		System.out.println("Deleting All Authors");

		authorRepository.deleteAll();
		res = new ResultData(true, "All authors were deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// ------------------- Update a Author
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/author/", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAuthor(@RequestBody Author author) {
		ResultData res ;
		System.out.println("Updating Author " + author.getAuthorId());

		Author myAuthor = authorRepository.findOne(author.getAuthorId());

		if (myAuthor == null) {
			res = new ResultData(false, "Author with id " + author.getAuthorId() + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		myAuthor.setFirstName(author.getFirstName());
		myAuthor.setLastName(author.getLastName());
		myAuthor.setDateOfBirth(author.getDateOfBirth());
		myAuthor.setLinkWiki(author.getLinkWiki());
		
		authorRepository.save(myAuthor);
		
		res = new ResultData(true, "Author id "  + author.getAuthorId() +" were update successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// -------------------Retrieve Single author
	// http://127.0.0.1:8080/author/1--------------------------------------------------------

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
}
