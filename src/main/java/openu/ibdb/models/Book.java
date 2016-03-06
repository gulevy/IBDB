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
	
//	@Column(name = "release_date", nullable = false)
//	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
//	private ZonedDateTime releaseDate;
	

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
	
}




//b_id INT NOT NULL AUTO_INCREMENT,
//b_name VARCHAR(20) NOT NULL,
//a_id INT,
//b_rdate DATE NOT NULL,
//b_publisher VARCHAR(20) NOT NULL,
//c_id INT,
//b_abstract VARCHAR(8000),
//PRIMARY KEY ( b_id ),
//FOREIGN KEY (a_id) REFERENCES authors(a_id),
//FOREIGN KEY (c_id) REFERENCES categories(c_id));