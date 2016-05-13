package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.Proposal;

//This annotation is responsible for exposing this repository interface as a RESTFul resource.
@RepositoryRestResource
public interface ProposalRepository extends CrudRepository<Proposal, Integer> {
	Collection<Proposal> findByProposalId(int proposalId);
	
}
