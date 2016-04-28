package openu.ibdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import openu.ibdb.models.Book;
import openu.ibdb.models.Review;
import openu.ibdb.repositories.BookRepository;
import openu.ibdb.repositories.ReviewRepository;

@RestController
public class ReviewController {

	@RequestMapping("/reviews")
	public Iterable<Review> reviews() {
		return this.reviewRepository.findAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/review/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<Void> createReview(@RequestBody Review review, @PathVariable("bookId") int bookId) {
		System.out.println("Creating review " + review.getReviewId());
		Book myBook = bookRepository.findOne(bookId) ;
		
		if (myBook == null) {
			System.out.println("cannot find request book for book id " + bookId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

		review.setBook(myBook);
		reviewRepository.save(review);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// delete review by id http://127.0.0.1:8080/review/1
	@RequestMapping(value = "/review/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Review> deleteReview(@PathVariable("id") int id) {
		System.out.println("Deleting review with id " + id);

		Review review = reviewRepository.findOne(id);
		if (review == null) {
			System.out.println("Unable to delete. Review with id " + id + " not found");
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}

		reviewRepository.delete(id);
		return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All reviews
	// --------------------------------------------------------

	@RequestMapping(value = "/reviews/", method = RequestMethod.DELETE)
	public ResponseEntity<Review> deleteAllReviews() {
		System.out.println("Deleting All Reviews");

		reviewRepository.deleteAll();
		return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Update a review
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/review/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateReview(@RequestBody Review review) {
		System.out.println("Updating Review " + review.getReviewId());

		Review myReview = reviewRepository.findOne(review.getReviewId());

		if (myReview == null) {
			System.out.println("proposal with id " + review.getReviewId() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		//myReview.setBookId(review.getBook().getBookId()));
		myReview.setUser(review.getUser());
		myReview.setRating(review.getRating());
		myReview.setReviewComment(review.getReviewComment());
		
		reviewRepository.save(myReview);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Retrieve Single review
	// http://127.0.0.1:8080/review/1--------------------------------------------------------

	@RequestMapping(value = "/review/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Review> getReview(@PathVariable("id") int id) {
		System.out.println("Fetching Review with id " + id);
		Review review = reviewRepository.findOne(id);
		if (review == null) {
			System.out.println("review with id " + id + " not found");
			return new ResponseEntity<Review>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Review>(review, HttpStatus.OK);
	}
	
	
	 @Autowired BookRepository bookRepository;
	 @Autowired ReviewRepository reviewRepository;
}
