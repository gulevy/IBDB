package openu.ibdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue
	@JoinColumn(name="review_id")
	private int reviewId;
	@ManyToOne
	@JoinColumn(name="book_id")
	@JsonIgnore
	private Book book;
	//private int bookId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	//private int userId;
	private int rating;
	private String reviewComment;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	/*	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}*/
/*	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}*/
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getReviewComment() {
		return reviewComment;
	}
	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}
}