package openu.ibdb.controllers;

import java.util.Collection;

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

import openu.ibdb.models.Proposal;
import openu.ibdb.models.ProposalState;
import openu.ibdb.models.ProposalState.Status;
import openu.ibdb.models.ResultData;
import openu.ibdb.repositories.ProposalRepository;
import openu.ibdb.repositories.ProposalStateRepository;

//This class responsible for proposal state web services actions
@RestController
public class ProposalStateController {

	@RequestMapping("/proposalsStates")
	public Iterable<ProposalState> proposalStates() {
		return this.proposalStateRepository.findAll();
	}
	
	@RequestMapping(value = "/proposalsStates/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<ProposalState>> getProposalStates(@PathVariable("id") int id) {
		System.out.println("Fetching Propasal with id " + id);
		//get all states for specific proposal
		Collection<ProposalState> states = proposalStateRepository.findByProposalProposalId(id);
		if (states == null) {
			System.out.println("proposal with id " + id + " not found");
			return new ResponseEntity<Collection<ProposalState>>(HttpStatus.OK);
		}
		return new ResponseEntity<Collection<ProposalState>>(states, HttpStatus.OK);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/proposalsStates/{id}", method = RequestMethod.POST)

	public ResponseEntity<String> createProposalState(@PathVariable("id") int id,@RequestBody ProposalState proposalState) {
		ResultData res ;
		System.out.println("Creating proposal state for proposal  " + id);
		
		//trying to get proposal to whom we want to add state
		Proposal myProposal = proposalRepository.findOne(id);

		if (myProposal == null) {
			res = new ResultData(false, "A Proposal state with id " + id + " does not exist");
	         return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		
		if (proposalStateRepository.findOne(proposalState.getStateId()) != null) {
			 res = new ResultData(false, "A Proposal state with id " + proposalState.getStateId() + " already exist");
	         return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
		}
		
		proposalState.setProposal(myProposal);
		proposalStateRepository.save(proposalState);
		
		
		sendMail(myProposal.getBook().getName(),myProposal.getUser().getPoints(), proposalState.getComment() , proposalState.getProposalStatus(),myProposal.getUser().getUserName());
		
//		if (proposal.getProposalStatus() == Status.approved) {
//			
//			//update your point 10 points for each proposal
//			myProposal.getUser().setPoints(myProposal.getUser().getPoints() + 10);
//			
//			//check if user pass the limitation point in order to convert into ibdb admin
//			if ((myProposal.getUser().getPoints() >= openu.ibdb.Application.adminPointsLimit) && (myProposal.getUser().getUserType() != UserType.administrator)) {
//				myProposal.getUser().setUserType(UserType.administrator);
//			}
//		}
		
		res = new ResultData(true, "Proposal state was added successfully");
		return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
	}

	//send mail to user who add proposal with the state of the proposal he added
	public void sendMail(String bookName,int point , String msgText,Status staus,String to) {
			try {
		    	   MimeMessage msg = javaMailServer.createMimeMessage();
		           MimeMessageHelper helper = new MimeMessageHelper(msg,true);
		           helper.setSubject("IBDB - updates");
		           helper.setSubject("Your book proposal for book " + bookName  + " was moved to " + staus.toString() + " state");
		         
		           // Set To: header field of the header.
		           helper.setTo(new InternetAddress(to));

		           if (staus == Status.approved) {
		        	   // Now set the actual message
			           helper.setText("Your proposal was " + staus.toString() + " \n you got another 10 points , total point status: " + (point + 10) + ".\n"
			        		   + "reason: " + msgText + " \n" 
			        		   + "Thank you for your contribution,\n IBDB team.");
		           } else if (staus == Status.info){
		        	   helper.setText("Your proposal was moved to " + staus.toString() +  " state.\n"
		        	   		+ "reason: " + msgText + "\n "
		        	   		+ "please edit your proposal and fix the proposal detail.\n"
		        	   		+ "Thanks,\n IBDB team.");
		           } else if (staus == Status.denied){
		        	   helper.setText("Your proposal was " + staus.toString() +  ".\n"
		        	   		+ "reason: " + msgText + "\n "
		        	   		+ "you will not be able to edit your proposal.\n"
		        	   		+ "Thanks,\n IBDB team.");
		           }
		                  
		           javaMailServer.send(msg);
		           
			} catch (Exception e) {
					System.out.println("unable to send maessage");
			}
		}

	@Autowired
	ProposalStateRepository proposalStateRepository;
	
	@Autowired
	ProposalRepository proposalRepository;
	
	@Autowired
	JavaMailSender javaMailServer;
}
