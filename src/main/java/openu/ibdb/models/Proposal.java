package openu.ibdb.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "proposals")
public class Proposal {
	@Id
	@GeneratedValue
	private int proposalId;
		
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="book_id")
	private Book book;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL)
	private List<ProposalState> stateHistory;
	

	public List<ProposalState> getStateHistory() {
		return stateHistory;
	}
	public void setStateHistory(List<ProposalState> stateHistory) {
		this.stateHistory = stateHistory;
	}
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
}
