package openu.ibdb;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



/**
 * declare spring application. activate auto configuration . 
 * need to add pring-boot-starter-web in pom file
 * @author gulevy
 *
 */
@SpringBootApplication
public class Application {
	public static String BOOK_IMG_PATH = "src\\main\\resources\\static\\assets\\images\\books";
	public static String USER_IMG_PATH = "src\\main\\resources\\static\\assets\\images\\users";
	public static int adminPointsLimit = 10000;
	
	public static void main(String[] args) {
		// will ran the application . build all singleton beans
		SpringApplication.run(Application.class, args);
	}

	//will create abean from that method
	@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			File f = new File(BOOK_IMG_PATH);
			if (!f.exists()) {
				f.mkdir();
			}
			
			f = new File(USER_IMG_PATH);
			if (!f.exists()) {
				f.mkdir();
			}

		};
		
	}
	
	

}
