package openu.ibdb.controllers;

import org.apache.hadoop.mapred.gethistory_jsp;
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

import openu.ibdb.models.Book;
import openu.ibdb.models.ResultData;
import openu.ibdb.models.Review;
import openu.ibdb.models.User.UserType;
import openu.ibdb.repositories.BookRepository;
import openu.ibdb.repositories.ReviewRepository;
import openu.ibdb.repositories.UserRepository;

/**
 * This class responsible for review web services actions
 * @author gulevy
 *
 */
@RestController
public class ReviewController {

	@RequestMapping("/reviews")
	public Iterable<Review> reviews() {
		return this.reviewRepository.findAll();
	}

	/**
	 * Create new review in db.
	 * @param review - review data
	 * @param bookId - for which book to add the review
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/review/{bookId}", method = RequestMethod.POST)
	public ResponseEntity<String> createReview(@RequestBody Review review, @PathVariable("bookId") int bookId) {
		ResultData res ;
		System.out.println("Creating review " + review.getReviewId());
		Book myBook = bookRepository.findOne(bookId) ;
		
		if (myBook == null) {		
			res = new ResultData(false, "Cannot find request book id " + bookId + " therefore cannont add a review");
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		//calculate review number for book
	    int totalReview = myBook.getReviews().size() + 1;
	  	
		myBook.setRate(((myBook.getRate() + review.getRating()) / totalReview));
		
		review.setBook(myBook);
		//on each review user is getting 5 points
		review.getUser().setPoints(review.getUser().getPoints() + 5);
		
		if ((review.getUser().getPoints() >= openu.ibdb.Application.adminPointsLimit) && (review.getUser().getUserType() != UserType.administrator)) {
			review.getUser().setUserType(UserType.administrator);
		}
		
		userRepository.save(review.getUser());	
		reviewRepository.save(review);
		
		res = new ResultData(true, "Review was added successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	/**
	 * delete review by id http://127.0.0.1:8080/review/1
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/review/{id}/{bookId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReview(@PathVariable("id") int id, @PathVariable("bookId") int bookId) {
		ResultData res ;
		System.out.println("Deleting review with id " + id);

		//check if review exist
		Review review = reviewRepository.findOne(id);
		if (review == null) {
			res = new ResultData(false, "Delete review failed , Cannot find request review " + id );
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		
		//delete review by id
		reviewRepository.delete(id);
		
		//update book rate		
		Book myBook = bookRepository.findOne(bookId) ;
		
		if (myBook == null) {		
			res = new ResultData(false, "Cannot find request book id " + bookId + " therefore cannont update rate for book");
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		
		//calculate review number for book
		float sumRate =0;
		int count =0;
		for (Review rev : myBook.getReviews()) {
			sumRate +=rev.getRating();
			++count;
		}
		
	    float rate = 0;  	
	    if (count >0){
	    	rate  = sumRate/count;
	    }
	    
	    myBook.setRate(rate);
	    bookRepository.save(myBook);
		
		res = new ResultData(true, "Review was deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}
	
	/**
	 * update an existing review
	 * @param review
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/review/{bookId}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateReview(@RequestBody Review review ,  @PathVariable("bookId") int bookId) {
		ResultData res ;
		System.out.println("Updating Review " + review.getReviewId());

		Review myReview = reviewRepository.findOne(review.getReviewId());

		if (myReview == null) {
			System.out.println("review with id " + review.getReviewId() + " not found");
			res = new ResultData(false, "Update review was failed cannot find review id " + review.getReviewId());
	        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		float rateBefore = myReview.getRating();
		
		//update review property
		myReview.setUser(review.getUser());
		myReview.setRating(review.getRating());
		myReview.setReviewComment(review.getReviewComment());
		
		reviewRepository.save(myReview);
		
		
		if (rateBefore != review.getRating()) {
			//only if the rate was edit only then i want to update also book		
			Book myBook = bookRepository.findOne(bookId) ;
			
			if (myBook == null) {		
				res = new ResultData(false, "Cannot find request book id " + bookId + " therefore cannont update also book rate");
		        return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
			}
			
			//calculate review number for book
			float sumRate =0;
			int count =0;
			for (Review rev : myBook.getReviews()) {
				sumRate +=rev.getRating();
				++count;
			}
			
		    float rate = 0;  	
		    if (count >0){
		    	rate  = sumRate/count;
		    }
		    
		    myBook.setRate(rate);
		    bookRepository.save(myBook);
			
		}
		
		

		res = new ResultData(true, "Review was updated successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	/**
	 * Retrieve Single review by id . 
	 * @param id - review id
	 * @return
	 */
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

	@Autowired UserRepository userRepository;
	@Autowired BookRepository bookRepository;
	@Autowired ReviewRepository reviewRepository;
}
