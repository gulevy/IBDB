package openu.ibdb.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue
	private int bookId;
	private String name;
	private int authourId;
	
	private String imageName;
	
//	@Column(name = "release_date", nullable = false)
//	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
//	private ZonedDateTime releaseDate;
	
	private int categoryId;

	private String publisherName;
	
	private String bookAbstract;
	
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
	
	public int getAuthourId() {
		return authourId;
	}
	public void setAuthourId(int authourId) {
		this.authourId = authourId;
	}
	
//	public ZonedDateTime getReleaseDate() {
//		return releaseDate;
//	}
//	public void setReleaseDate(ZonedDateTime releaseDate) {
//		this.releaseDate = releaseDate;
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
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}