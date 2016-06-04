package openu.ibdb.models;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue
	@Column(name= "author_id")
	private int authorId;
	private String firstName;
	private String lastName;
    //http url for wiki page
	private String linkWiki;
	private Date dateOfBirth;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Book> books;
	
	public Set<Book> getBooks() {
		return books;
	}
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLinkWiki() {
		return linkWiki;
	}
	public void setLinkWiki(String linkWiki) {
		this.linkWiki = linkWiki;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
}