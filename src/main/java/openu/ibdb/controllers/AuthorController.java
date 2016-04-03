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

import openu.ibdb.models.Author;
import openu.ibdb.models.User;
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

	public ResponseEntity<Void> createAuthor(@RequestBody Author author) {
		System.out.println("Creating author " + author.getFirstName());

		if (authorRepository.findOne(author.getAuthorId()) != null) {
			System.out.println("A author with name " + author.getFirstName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		authorRepository.save(author);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// delete author by id http://127.0.0.1:8080/author/1
	@RequestMapping(value = "/author/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteAuthor(@PathVariable("id") int id) {
		System.out.println("Deleting author with id " + id);

		Author author = authorRepository.findOne(id);
		if (author == null) {
			System.out.println("Unable to delete. Author with id " + id + " not found");
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}

		authorRepository.delete(id);
		return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All authors
	// --------------------------------------------------------

	@RequestMapping(value = "/authors/", method = RequestMethod.DELETE)
	public ResponseEntity<Author> deleteAllAuthors() {
		System.out.println("Deleting All Authors");

		authorRepository.deleteAll();
		return new ResponseEntity<Author>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Update a Author
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/author/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateAuthor(@RequestBody Author author) {
		System.out.println("Updating Author " + author.getAuthorId());

		Author myAuthor = authorRepository.findOne(author.getAuthorId());

		if (myAuthor == null) {
			System.out.println("author with id " + author.getAuthorId() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		myAuthor.setFirstName(author.getFirstName());
		myAuthor.setLastName(author.getLastName());
		myAuthor.setLinkWiki(author.getLinkWiki());
		
		authorRepository.save(myAuthor);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Retrieve Single author
	// http://127.0.0.1:8080/author/1--------------------------------------------------------

	@RequestMapping(value = "/author/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Author> getAuthor(@PathVariable("id") int id) {
		System.out.println("Fetching Author with id " + id);
		Author author = authorRepository.findOne(id);
		if (author == null) {
			System.out.println("author with id " + id + " not found");
			return new ResponseEntity<Author>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Author>(author, HttpStatus.OK);
	}

	@Autowired
	AuthorRepository authorRepository;
}
