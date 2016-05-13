package openu.ibdb.controllers;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
import openu.ibdb.models.Proposal.Status;
import openu.ibdb.models.ResultData;
import openu.ibdb.models.User;
import openu.ibdb.repositories.AuthorRepository;
import openu.ibdb.repositories.ProposalRepository;
import openu.ibdb.repositories.UserRepository;

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
		
		//fix detached entity passed to persist: openu.ibdb.models.User  exception
		User myUser = userRepository.findOne(proposal.getUser().getUserId());
		proposal.setUser(myUser);
		
		//if author not exist only then want to create new author
		Author author = authorRepository.findOne(proposal.getBook().getAuthor().getAuthorId());
		
		if (author == null) {
			authorRepository.save(proposal.getBook().getAuthor()); 
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
//		myProposal.getBook().setImageName(proposal.getBook().getImageName());
		myProposal.getBook().setName(proposal.getBook().getName());
		myProposal.getBook().setPublisherName(proposal.getBook().getPublisherName());
		myProposal.getBook().setReleaseDate(proposal.getBook().getReleaseDate());
		
		myProposal.setProposalDate(proposal.getProposalDate());
		
		if (myProposal.getProposalStatus() != proposal.getProposalStatus() ) {
			// proposal status was changed 
			statusChange = true;	
		} else {
			statusChange = false;
		}
	
		myProposal.setProposalStatus(proposal.getProposalStatus());
		proposalRepository.save(myProposal);
		
		if (statusChange) {
		   sendMail(myProposal.getBook().getName(),myProposal.getProposalStatus(),myProposal.getUser().getUserName());
		}
		
		res = new ResultData(true, "Proposal id "  + proposal.getProposalId() +" were update successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	// -------------------Retrieve Single proposal
	// http://127.0.0.1:8080/proposal/1--------------------------------------------------------

	public void sendMail(String bookName,Status staus,String to) {
		try {
	    	   MimeMessage msg = javaMailServer.createMimeMessage();
	           MimeMessageHelper helper = new MimeMessageHelper(msg,true);
	           helper.setSubject("IBDB - updates");
	           helper.setSubject("Your book proposal for book " + bookName  + " was " + staus.toString());
	         
	           // Set To: header field of the header.
	           helper.setTo(new InternetAddress(to));

	           if (staus == Status.approved) {
	        	   // Now set the actual message
		           helper.setText("Your proposal was " + staus.toString() + " .\n Thank you for your contribution,\n IBDB team.");
	           } else if (staus == Status.denied){
	        	   helper.setText("Your proposal was " + staus.toString() +  ".\n It can be due to the following reason:\n 1. Book already exist \n 2. book information is not correct\n thanks,\n IBDB team.");
	           }
	                  
	           javaMailServer.send(msg);
	           
		} catch (Exception e) {
				System.out.println("unable to send maessage");
		}
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
	UserRepository userRepository;
	
	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	JavaMailSender javaMailServer;
}
