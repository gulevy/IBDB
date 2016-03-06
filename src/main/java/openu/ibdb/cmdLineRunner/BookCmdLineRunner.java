package openu.ibdb.cmdLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import openu.ibdb.models.Book;
import openu.ibdb.repositories.BookRepository;

@Component
public class BookCmdLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		for (Book b : this.bookRepository.findAll()) {
			System.out.println(b.toString());
		}
		
	}
	
	
	
	
	@Autowired BookRepository  bookRepository;

}
