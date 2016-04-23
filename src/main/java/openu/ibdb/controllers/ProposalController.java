package openu.ibdb.controllers;

<<<<<<< HEAD
=======
import java.io.File;

>>>>>>> origin/master
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

<<<<<<< HEAD
import com.google.gson.Gson;

import openu.ibdb.models.Proposal;
import openu.ibdb.models.ResultData;
=======
import openu.ibdb.models.Proposal;
>>>>>>> origin/master
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

<<<<<<< HEAD
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
=======
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
>>>>>>> origin/master
	}

	// ------------------- Delete All proposals
	// --------------------------------------------------------

	@RequestMapping(value = "/proposals/", method = RequestMethod.DELETE)
<<<<<<< HEAD
	public ResponseEntity<String> deleteAllProposals() {
		ResultData res ;
		System.out.println("Deleting All Proposals");

		proposalRepository.deleteAll();
		res = new ResultData(true, "All proposals were deleted successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// ------------------- Update a Proposal
=======
	public ResponseEntity<Proposal> deleteAllProposals() {
		System.out.println("Deleting All Authors");

		proposalRepository.deleteAll();
		return new ResponseEntity<Proposal>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Update a proposal
>>>>>>> origin/master
	// --------------------------------------------------------
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proposal/", method = RequestMethod.PUT)
<<<<<<< HEAD
	public ResponseEntity<String> updateProposal(@RequestBody Proposal proposal) {
		ResultData res ;
=======
	public ResponseEntity<Void> updateProposal(@RequestBody Proposal proposal) {
>>>>>>> origin/master
		System.out.println("Updating Proposal " + proposal.getProposalId());

		Proposal myProposal = proposalRepository.findOne(proposal.getProposalId());

		if (myProposal == null) {
<<<<<<< HEAD
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
		myProposal.getUser().setUsername(proposal.getUser().getUsername());
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
=======
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
>>>>>>> origin/master
		}
		return new ResponseEntity<Proposal>(proposal, HttpStatus.OK);
	}

<<<<<<< HEAD
	@Autowired
	ProposalRepository proposalRepository;
=======
	 @Autowired ProposalRepository proposalRepository;
>>>>>>> origin/master
}
