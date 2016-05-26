package openu.ibdb.controllers;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import openu.ibdb.models.Author;
import openu.ibdb.models.Proposal;
import openu.ibdb.models.ProposalState.Status;
import openu.ibdb.models.ResultData;
import openu.ibdb.models.User;
import openu.ibdb.repositories.AuthorRepository;
import openu.ibdb.repositories.ProposalRepository;
import openu.ibdb.repositories.ProposalStateRepository;
import openu.ibdb.repositories.UserRepository;

@RestController
public class ProposalController {

	@RequestMapping("/proposals")
	public Iterable<Proposal> proposals() {
		Iterable<Proposal> proposals =  this.proposalRepository.findAll();
		
		//sort each proposal state history
		for (Proposal proposal : proposals) {
			Collections.reverse(proposal.getStateHistory());
		}
		
		return proposals;
	}
	
	@RequestMapping(value = "/proposal/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Proposal>> getUserProposals(@PathVariable("id") int id) {
		System.out.println("Fetching Propasal with id " + id);
		Collection<Proposal> proposals = proposalRepository.findByUserUserId(id);
		if (proposals == null) {
			System.out.println("Cannot find proposals for user id " + id + " not found");
			return new ResponseEntity<Collection<Proposal>>(HttpStatus.OK);
		}
		
		//sort each proposal state history
		for (Proposal proposal : proposals) {
			Collections.reverse(proposal.getStateHistory());
		}
		
		return new ResponseEntity<Collection<Proposal>>(proposals, HttpStatus.OK);
	}

	
	@RequestMapping("/proposal/{state}")
	public Iterable<Proposal> proposals(@PathVariable("state") String state ,@RequestBody User user) {
		Iterable<Proposal> proposals;
		
		if (state == "all") {
			proposals =  this.proposalRepository.findAll();
		} else {
			proposals =  this.proposalRepository.findAllByStateHistoryProposalStatusAndUserUserNameOrderByStateHistoryStateIdAsc(Status.valueOf(state), user.getUserName());
		}
		 
		//sort each proposal state history
		for (Proposal proposal : proposals) {
			Collections.reverse(proposal.getStateHistory());
		}
		
		return proposals;
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
		
		//fix detached entity passed to persist: openu.ibdb.models.User  exception
		User myUser = userRepository.findOne(proposal.getUser().getUserId());
		proposal.setUser(myUser);
		
		//if author not exist only then want to create new author
		Author author = authorRepository.findOne(proposal.getBook().getAuthor().getAuthorId());
		
		if (author == null) {
			authorRepository.save(proposal.getBook().getAuthor()); 
		} else{
			proposal.getBook().setAuthor(author);
		}
		
		Proposal  p = proposalRepository.save(proposal);
		
		System.out.println("pid " + p.getProposalId());
		
//		//when you add new proposal you will always have 1 state
		proposal.getStateHistory().get(0).setProposal(p);
		proposalStateRepository.save(proposal.getStateHistory().get(0));
//		
		
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

		proposal.getBook().setAuthor(null);
		proposal.setUser(null);
		proposalRepository.delete(proposal);
		
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
		boolean statusChange = false;
		System.out.println("Updating Proposal " + proposal.getProposalId());

		Proposal myProposal = proposalRepository.findOne(proposal.getProposalId());

		if (myProposal == null) {
			res = new ResultData(false, "Proposal with id " + proposal.getProposalId() + " not found");
			return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}

		//if author not exist only then want to create new author
		Author author = authorRepository.findOne(proposal.getBook().getAuthor().getAuthorId());
		
		if (author == null) {
			myProposal.getBook().getAuthor().setDateOfBirth(proposal.getBook().getAuthor().getDateOfBirth());
			myProposal.getBook().getAuthor().setFirstName(proposal.getBook().getAuthor().getFirstName());
			myProposal.getBook().getAuthor().setLastName(proposal.getBook().getAuthor().getLastName());
			myProposal.getBook().getAuthor().setLinkWiki(proposal.getBook().getAuthor().getLinkWiki());
		} else {
			myProposal.getBook().setAuthor(author);
		}
		
		myProposal.getBook().setBookAbstract(proposal.getBook().getBookAbstract());
		myProposal.getBook().setBookCategory(proposal.getBook().getBookCategory());
		myProposal.getBook().setName(proposal.getBook().getName());
		myProposal.getBook().setPublisherName(proposal.getBook().getPublisherName());
		myProposal.getBook().setReleaseDate(proposal.getBook().getReleaseDate());
		
//		myProposal.setProposalDate(proposal.getProposalDate());
//		
//		if (myProposal.getProposalStatus() != proposal.getProposalStatus() ) {
//			// proposal status was changed 
//			statusChange = true;	
//			
//			if (proposal.getProposalStatus() == Status.approved) {
//				
//				//update your point 10 points for each proposal
//				myProposal.getUser().setPoints(myProposal.getUser().getPoints() + 10);
//				
//				//check if user pass the limitation point in order to convert into ibdb admin
//				if ((myProposal.getUser().getPoints() >= openu.ibdb.Application.adminPointsLimit) && (myProposal.getUser().getUserType() != UserType.administrator)) {
//					myProposal.getUser().setUserType(UserType.administrator);
//				}
//			}
//			
//		} else {
//			statusChange = false;
//		}
//			
//		myProposal.setProposalStatus(proposal.getProposalStatus());
		proposalRepository.save(myProposal);
			
		res = new ResultData(true, "Proposal id "  + proposal.getProposalId() +" were update successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

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
	
	@Autowired
	ProposalStateRepository proposalStateRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	JavaMailSender javaMailServer;
}
