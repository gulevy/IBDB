package openu.ibdb.controllers;

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

import com.google.gson.Gson;

import openu.ibdb.models.Proposal;
import openu.ibdb.models.ResultData;
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

	public ResponseEntity<String> createProposal(@RequestBody Proposal proposal) {
		ResultData res ;
		System.out.println("Creating proposal " + proposal.getProposalId());

		if (proposalRepository.findOne(proposal.getProposalId()) != null) {
			 res = new ResultData(false, "A Proposal with id " + proposal.getProposalId() + " already exist");
	         return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		proposalRepository.save(proposal);
		res = new ResultData(true, "Proposal was added successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// delete proposal by id http://127.0.0.1:8080/proposal/1
	@RequestMapping(value = "/proposal/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProposal(@PathVariable("id") int id) {
		ResultData res ;
		System.out.println("Deleting proposal with id " + id);

		Proposal proposal = proposalRepository.findOne(id);
		if (proposal == null) {	
			res = new ResultData(false, "Unable to delete. Proposal with id " + id + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		proposalRepository.delete(id);
		res = new ResultData(true, "Proposal id " + id + " was deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// ------------------- Delete All proposals
	// --------------------------------------------------------

	@RequestMapping(value = "/proposals/", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAllProposals() {
		ResultData res ;
		System.out.println("Deleting All Proposals");

		proposalRepository.deleteAll();
		res = new ResultData(true, "All proposals were deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// ------------------- Update a Proposal
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proposal/", method = RequestMethod.PUT)
	public ResponseEntity<String> updateProposal(@RequestBody Proposal proposal) {
		ResultData res ;
		System.out.println("Updating Proposal " + proposal.getProposalId());

		Proposal myProposal = proposalRepository.findOne(proposal.getProposalId());

		if (myProposal == null) {
			res = new ResultData(false, "Proposal with id " + proposal.getProposalId() + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

//		myProposal.getAuthor().setDateOfBirth(proposal.getAuthor().getDateOfBirth());
//		myProposal.getAuthor().setFirstName(proposal.getAuthor().getFirstName());
//		myProposal.getAuthor().setLastName(proposal.getAuthor().getLastName());
//		myProposal.getAuthor().setLinkWiki(proposal.getAuthor().getLinkWiki());
		
		myProposal.getBook().setBookAbstract(proposal.getBook().getBookAbstract());
//		myProposal.getBook().setBookCategory(bookCategory);
		myProposal.getBook().setImageName(proposal.getBook().getImageName());
		myProposal.getBook().setName(proposal.getBook().getName());
		myProposal.getBook().setPublisherName(proposal.getBook().getPublisherName());
		myProposal.getBook().setReleaseDate(proposal.getBook().getReleaseDate());
		
		
		myProposal.setProposalDate(proposal.getProposalDate());
		myProposal.setProposalStatus(proposal.getProposalStatus());
		
		myProposal.getUser().setDateOfBirth(proposal.getUser().getDateOfBirth());
		myProposal.getUser().setFirstName(proposal.getUser().getFirstName());
		myProposal.getUser().setLastName(proposal.getUser().getLastName());
		myProposal.getUser().setPassword(proposal.getUser().getPassword());
		myProposal.getUser().setUserName(proposal.getUser().getUserName());
		myProposal.getUser().setPoints(proposal.getUser().getPoints());
		myProposal.getUser().setUserType(proposal.getUser().getUserType());
		myProposal.getUser().setGender(proposal.getUser().getGender());

		proposalRepository.save(myProposal);
		
		res = new ResultData(true, "Proposal id "  + proposal.getProposalId() +" were update successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// -------------------Retrieve Single proposal
	// http://127.0.0.1:8080/proposal/1--------------------------------------------------------

	@RequestMapping(value = "/proposal/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Proposal> getProposal(@PathVariable("id") int id) {
		System.out.println("Fetching Propasal with id " + id);
		Proposal proposal = proposalRepository.findOne(id);
		if (proposal == null) {
			System.out.println("proposal with id " + id + " not found");
			return new ResponseEntity<Proposal>(HttpStatus.OK);
		}
		return new ResponseEntity<Proposal>(proposal, HttpStatus.OK);
	}

	@Autowired
	ProposalRepository proposalRepository;
}
