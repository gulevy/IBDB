package openu.ibdb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import openu.ibdb.models.ResultData;
import openu.ibdb.models.User;
import openu.ibdb.repositories.UserRepository;

@RestController
public class UserController {
  
  @RequestMapping("/users")	
  public Iterable<User> Users() {
	  return this.userRepository.findAll();
  }
  
  @RequestMapping(value = "/user/", method = RequestMethod.POST)
  public ResponseEntity<String> createUser(@RequestBody User User) {
      System.out.println("Creating User " + User.getFirstName());
      ResultData res;

      if (userRepository.findByUsername(User.getUsername()) != null) {
          System.out.println("A User with name " + User.getUsername() + " already exist");
          res = new ResultData(false, "username already exist");
          
          return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.CONFLICT); 
      }

      userRepository.save(User);

      res = new ResultData(true, "user was added");
      return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.CREATED); 
  
  }
  
  //delete User by id  http://127.0.0.1:8080/User/1
  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
      System.out.println("Deleting User with id " + id);

      User User = userRepository.findOne(id);
      if (User == null) {
          System.out.println("Unable to delete. User with id " + id + " not found");
          return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
      }

      userRepository.delete(id);
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }
  
  @RequestMapping(value = "/user/auth/{username}/{password}", method = RequestMethod.GET)
  public ResponseEntity<String> auth(@PathVariable("username") String username , @PathVariable("password") String password)  {

      User user = userRepository.findByUsername(username);
      
//      if (user == null) {
//    	  return new ResponseEntity<User>(User, HttpStatus.OK);
//      } else {
//    	  return new ResponseEntity<User>(User, HttpStatus.OK);
//      }
      
      ResultData res;
          
      if (user == null) {
          System.out.println("Unable to find username = " + username );
          
          res = new ResultData(false, "Unable to find username = " + username );
          return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.NOT_FOUND);
      }
      
      if (user.getPassword().toString().equalsIgnoreCase(password)) {
    	  res = new ResultData(true, "password correct");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.OK);
      } else {
    	  res = new ResultData(false, "password incorrect");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.NOT_FOUND);
      }
  }
  
  
  @RequestMapping(value = "/user/authenticate/{username}/{password}", method = RequestMethod.GET , produces = "application/json")
  public ResponseEntity<String> authenticate(@PathVariable("username") String username , @PathVariable("password") String password)  {

      User user = userRepository.findByUsername(username);
      
//      if (user == null) {
//    	  return new ResponseEntity<User>(User, HttpStatus.OK);
//      } else {
//    	  return new ResponseEntity<User>(User, HttpStatus.OK);
//      }
      
      ResultData res;
          
      if (user == null) {
          System.out.println("Unable to find username = " + username );
          
          res = new ResultData(false, "Unable to find username = " + username );
          return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.NOT_FOUND);
      }
      
      if (user.getPassword().toString().equalsIgnoreCase(password)) {
    	  res = new ResultData(true, "password correct");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.OK);
      } else {
    	  res = new ResultData(false, "password incorrect");
    	  return new ResponseEntity<String> (new Gson().toJson(res) ,HttpStatus.NOT_FOUND);
      }
  }
	    
   
  //------------------- Delete All Users --------------------------------------------------------
    
  @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteAllUsers() {
      System.out.println("Deleting All Users");

      userRepository.deleteAll();
      return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }
  
  
  //------------------- Update a User --------------------------------------------------------
  
  @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
  public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User User) {
      System.out.println("Updating User " + id);
        
      User myUser = userRepository.findOne(id);
        
      if (myUser==null) {
          System.out.println("User with id " + id + " not found");
          return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
      }

      myUser.setFirstName(User.getFirstName());
    
      userRepository.save(User);
      
      return new ResponseEntity<User>(User, HttpStatus.OK);
  }
 
//-------------------Retrieve Single User  http://127.0.0.1:8080/User/1--------------------------------------------------------
  
  @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<User> getUser(@PathVariable("id") int id) {
      System.out.println("Fetching User with id " + id);
      User User =  userRepository.findOne(id);
      if (User == null) {
          System.out.println("User with id " + id + " not found");
          return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<User>(User, HttpStatus.OK);
  }
  
  @Autowired UserRepository userRepository;
}
