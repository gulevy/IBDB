package openu.ibdb.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import openu.ibdb.models.Proposal;
import openu.ibdb.repositories.ProposalRepository;

@RestController
public class ProposalController {

	@RequestMapping("/proposals")
	public Iterable<Proposal> proposals() {
		return this.proposalRepository.findAll();
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proposal/", method = RequestMethod.POST)

	public ResponseEntity<Void> createProposal(@RequestBody Proposal proposal) {
		System.out.println("Creating proposal " + proposal.getProposalId());

		if (proposalRepository.findOne(proposal.getProposalId()) != null) {
			System.out.println("A proposal with ID " + proposal.getProposalId() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		proposalRepository.save(proposal);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// delete author by id http://127.0.0.1:8080/proposal/1
	@RequestMapping(value = "/proposal/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Proposal> deleteProposal(@PathVariable("id") int id) {
		System.out.println("Deleting proposal with id " + id);

		Proposal proposal = proposalRepository.findOne(id);
		if (proposal == null) {
			System.out.println("Unable to delete. Proposal with id " + id + " not found");
			return new ResponseEntity<Proposal>(HttpStatus.NOT_FOUND);
		}

		proposalRepository.delete(id);
		return new ResponseEntity<Proposal>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All proposals
	// --------------------------------------------------------

	@RequestMapping(value = "/proposals/", method = RequestMethod.DELETE)
	public ResponseEntity<Proposal> deleteAllProposals() {
		System.out.println("Deleting All Authors");

		proposalRepository.deleteAll();
		return new ResponseEntity<Proposal>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Update a proposal
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proposal/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProposal(@RequestBody Proposal proposal) {
		System.out.println("Updating Proposal " + proposal.getProposalId());

		Proposal myProposal = proposalRepository.findOne(proposal.getProposalId());

		if (myProposal == null) {
			System.out.println("proposal with id " + proposal.getProposalId() + " not found");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}

		myProposal.setAuthorId(proposal.getAuthorId());
		myProposal.setBook(proposal.getBook());
		myProposal.setUser(proposal.getUser());
		myProposal.setProposalDate(proposal.getProposalDate());
		
		proposalRepository.save(myProposal);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Retrieve Single proposal
	// http://127.0.0.1:8080/author/1--------------------------------------------------------

	@RequestMapping(value = "/proposal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proposal> getProposal(@PathVariable("id") int id) {
		System.out.println("Fetching Proposal with id " + id);
		Proposal proposal = proposalRepository.findOne(id);
		if (proposal == null) {
			System.out.println("proposal with id " + id + " not found");
			return new ResponseEntity<Proposal>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Proposal>(proposal, HttpStatus.OK);
	}

	 @Autowired ProposalRepository proposalRepository;
}
