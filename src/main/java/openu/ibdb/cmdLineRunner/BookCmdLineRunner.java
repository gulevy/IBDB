package openu.ibdb.cmdLineRunner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import openu.ibdb.models.Author;
import openu.ibdb.models.Book;
import openu.ibdb.repositories.BookRepository;

@Component
public class BookCmdLineRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

//		Author a = new Author();
//		a.setFirstName("ggg");
//		a.setLastName("hhh");
//		a.setLinkWiki("gggg");
//		a.setDateOfBirth(new Date());
//		
//		Book b = new Book();
//		b.setAuthor(a);
//		b.setImageName("bla");
//		b.setCategoryId(3);
//		b.setBookAbstract("ggg");
//		b.setPublisherName("ggg");
//		b.setName("fghhjhjh");
//		b.setReleaseDate(new Date());
//		
//		bookRepository.save(b);
		
		for (Book b1 : this.bookRepository.findAll()) {
			System.out.println(b1.toString());
		}
	}
	
	@Autowired BookRepository  bookRepository;
}
