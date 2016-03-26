package openu.ibdb.cmdLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import openu.ibdb.models.BookCategory;
import openu.ibdb.repositories.BookCategoryRepository;

@Component
public class BookCategoryCmdLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		for (BookCategory b : this.bookCategoryRepository.findAll()) {
			System.out.println(b.toString());
		}	
	}
	
	@Autowired BookCategoryRepository  bookCategoryRepository;

}
