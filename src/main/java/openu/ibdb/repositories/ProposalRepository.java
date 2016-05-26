package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.Proposal;
import openu.ibdb.models.ProposalState.Status;

//This annotation is responsible for exposing this repository interface as a RESTFul resource.
@RepositoryRestResource
public interface ProposalRepository extends CrudRepository<Proposal, Integer> {
	
	Collection<Proposal>  findAllByOrderByStateHistoryStateIdAsc();
	
	Collection<Proposal>  findAllByStateHistoryProposalStatusOrderByStateHistoryStateIdAsc(Status s);
	Collection<Proposal>  findAllByStateHistoryProposalStatusAndUserUserNameOrderByStateHistoryStateIdAsc(Status s,String userName);
	
	Collection<Proposal> findByProposalId(int proposalId);
	Collection<Proposal> findByUserUserId(int userId);
	
}
