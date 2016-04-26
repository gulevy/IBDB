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

import openu.ibdb.models.ResultData;
import openu.ibdb.models.User;
import openu.ibdb.repositories.UserRepository;	

@RestController
public class UserController {
  
  @RequestMapping("/users")	
  public Iterable<User> users() {
	  return this.userRepository.findAll();
  }
  
  @RequestMapping(value = "/user/", method = RequestMethod.POST)
  public ResponseEntity<String> createUser(@RequestBody User user) {
	  ResultData res ;
      System.out.println("Creating User " + user.getFirstName());

      if (userRepository.findByUserName(user.getUserName()) != null) {
          System.out.println("A User with name " + user.getUserName() + " already exist");
          res = new ResultData(false, "A User with name " + user.getUserName() + " already exist");
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }

      userRepository.save(user);

      
      res = new ResultData(true, "Registration successful");
	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.CREATED);
      
  }
  
  //delete User by id  http://127.0.0.1:8080/User/1
  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
	  ResultData res ;
      System.out.println("Deleting User with id " + id);

      User User = userRepository.findOne(id);
      if (User == null) {
          System.out.println("Unable to delete. User with id " + id + " not found");
          res = new ResultData(false, "Cannot find user id " + id );
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }

      userRepository.delete(id);
      res = new ResultData(true, "user " + id + " been deleted successfully" );
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
  }
  
  @RequestMapping(value = "/user/authenticate/{username}/{password}", method = RequestMethod.GET)
  public ResponseEntity<String> auth(@PathVariable("username") String username , @PathVariable("password") String password)  {
      User user = userRepository.findByUserName(username);
      ResultData res;
          
      if (user == null) {
          System.out.println("Unable to find username = " + username );
          res = new ResultData(false, "Unable to find username = " + username );
          return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.OK);
      }
      
      if (user.getPassword().toString().equalsIgnoreCase(password)) {
    	  res = new ResultData(true, "password correct");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.OK);
      } else {
    	  res = new ResultData(false, "password incorrect");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.OK);
      }
  }
  	    
  
  //------------------- Delete All Users --------------------------------------------------------
    
  @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteAllUsers() {
	  ResultData res;
      System.out.println("Deleting All Users");

      userRepository.deleteAll();
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }
  
  //------------------- Update a User --------------------------------------------------------
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(value = "/user/", method = RequestMethod.PUT)
  public ResponseEntity<String> updateUser(@RequestBody User user) {
	  ResultData res;
      System.out.println("Updating User " + user.getUserId());
        
      User myUser = userRepository.findOne(user.getUserId());
        
      if (myUser==null) {
    	  res = new ResultData(false, "Cannot find user id " + user.getUserName() );
          return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
      }
      
      myUser.setFirstName(user.getFirstName());
      myUser.setLastName(user.getLastName());
      myUser.setUserName(user.getUserName());
      myUser.setPassword(user.getPassword());
      myUser.setPoints(user.getPoints());
      myUser.setUserType(user.getUserType());
      myUser.setGender(user.getGender());
      myUser.setDateOfBirth(user.getDateOfBirth());
      
      userRepository.save(myUser);
      
      res = new ResultData(true, "user " + user.getUserName() + " was update successfully"  );
      return new ResponseEntity<String>(new Gson().toJson(res),HttpStatus.OK);
  }
  
 
//-------------------Retrieve Single User  http://127.0.0.1:8080/User/1--------------------------------------------------------
  
  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable("id") int id) {
      System.out.println("Fetching User with id " + id);
      User myUser =  userRepository.findOne(id);
      if (myUser == null) {
          System.out.println("User with id " + id + " not found");
          return new ResponseEntity<User>(HttpStatus.OK);
      }

      return new ResponseEntity<User>(myUser, HttpStatus.OK);
  }
  
  @RequestMapping(value = "/user/username/{username}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
	  System.out.println("Fetching User with username " + username);
      User myUser =  userRepository.findByUserName(username);
      if (myUser == null) {
          System.out.println("User with username " + username + " not found");
          return new ResponseEntity<User>(HttpStatus.OK);
      }

      return new ResponseEntity<User>(myUser, HttpStatus.OK);
  }
  
  
  
  @Autowired UserRepository userRepository;
}
