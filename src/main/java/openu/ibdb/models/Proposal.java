package openu.ibdb.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {
	public enum status {pending, approved, denied};
	
	@Id
	@GeneratedValue
	private int proposalId;
	private int authorId;
	private int bookId;
	private int userId;
	private String proposalDate;
	
	@Enumerated(EnumType.STRING)
	private status proposalStatus;
	
	public int getProposalId() {
		return proposalId;
	}
	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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