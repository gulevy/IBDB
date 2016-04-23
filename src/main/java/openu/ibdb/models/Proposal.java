package openu.ibdb.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {
	public enum status {pending, approved, denied};
	
	@Id
	@GeneratedValue
	private int proposalId;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="book_id")
	private Book book;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	private String proposalDate;
	
	@Enumerated(EnumType.STRING)
	private status proposalStatus;
	
	public int getProposalId() {
		return proposalId;
	}
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
	}
	
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
	}
	public String getProposalDate() {
		return proposalDate;
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