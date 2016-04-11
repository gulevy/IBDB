package openu.ibdb.models;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue
	@JoinColumn(name="book_id")
	private int bookId;
	private String name;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="author_id")
	private Author author;
	
	private String imageName;

	private Date releaseDate;

	@ManyToOne
	@JoinColumn(name = "book_category_id")
	@JsonManagedReference
	private BookCategory bookCategory;
//	private int categoryId;
	
	private String publisherName;

	private String bookAbstract;
	
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

//	public int getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(int categoryId) {
//		this.categoryId = categoryId;
//	}

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
	
//	private BookCategory bookCategory;


//	@ManyToOne
//    @JoinColumn(name = "book_category_id")
//	 public BookCategory getBookCategory() {
//			return bookCategory;
//		}
//
//		public void setBookCategory(BookCategory bookCategory) {
//			this.bookCategory = bookCategory;
//		}

  
}