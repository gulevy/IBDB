package openu.ibdb.cmdLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import openu.ibdb.models.User;
import openu.ibdb.repositories.UserRepository;

@Component
public class UserCmdLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		for (User b : this.userRepository.findAll()) {
			System.out.println(b.toString());
		}
		
	}
	
	
	
	
	@Autowired UserRepository  userRepository;

}
