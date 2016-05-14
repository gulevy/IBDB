package openu.ibdb.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue
	@JoinColumn(name="book_id")
	private int bookId;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	private String imageName;

	private Date releaseDate;

	@ManyToOne
	@JoinColumn(name = "book_category_id")
	private BookCategory bookCategory;
	
	private String publisherName;

	@Size(max = 750)
	private String bookAbstract;
	
	@OneToOne(mappedBy = "book", cascade = CascadeType.ALL)
	@JsonIgnore
	private Proposal proposal;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	private Set<Review> reviews;
	
	private float rate = 0;
	
	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getBookAbstract() {
		return bookAbstract;
	}

	public void setBookAbstract(String bookAbstract) {
		this.bookAbstract = bookAbstract;
	}
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	} 
}