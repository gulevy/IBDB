package openu.ibdb.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
<<<<<<< HEAD
import javax.persistence.OneToOne;
=======
import javax.persistence.ManyToOne;
>>>>>>> origin/master
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {
	public enum status {pending, approved, denied};
	
	@Id
	@GeneratedValue
	@JoinColumn (name="proposal_id")
	private int proposalId;
<<<<<<< HEAD
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="book_id")
	private Book book;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
=======
	private int authorId;
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	//private int bookId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
//	private int userId;
>>>>>>> origin/master
	private String proposalDate;
	
	@Enumerated(EnumType.STRING)
	private status proposalStatus;
	

	public int getProposalId() {
		return proposalId;
	}
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
	}
<<<<<<< HEAD
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
=======
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
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
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
>>>>>>> origin/master
	}
	public String getProposalDate() {
		return proposalDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setProposalDate(String proposalDate) {
		this.proposalDate = proposalDate;
	}
	public status getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(status proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	
	
	

	
}