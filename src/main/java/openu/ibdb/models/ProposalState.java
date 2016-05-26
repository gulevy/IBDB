package openu.ibdb.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "proposalStates")
public class ProposalState  implements Comparable<ProposalState> {
	public enum Status {pending, approved, info, denied};
	
	@Id
	@GeneratedValue
	private int stateId;
	
	@ManyToOne
	@JoinColumn(name="proposal_id")
	@JsonIgnore
	private Proposal proposal;
		
	private String proposalDate;
	
	@Enumerated(EnumType.STRING)
	private Status proposalStatus;
	
	private String comment;
	
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getProposalDate() {
		return proposalDate;
	}
	public void setProposalDate(String proposalDate) {
		this.proposalDate = proposalDate;
	}
	public Status getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(Status proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	@Override
	public int compareTo(ProposalState state) {
		return Integer.valueOf(this.stateId).compareTo(state.stateId);
	}
	
	public Proposal getProposal() {
		return proposal;
	}
	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}
}