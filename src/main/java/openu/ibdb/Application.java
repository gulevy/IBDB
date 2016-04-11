package openu.ibdb;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static String BOOK_IMG_PATH = "src\\main\\resources\\static\\assets\\images\\books";
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return (String[] args) -> {
			File f = new File(BOOK_IMG_PATH);
			if (!f.exists()) {
				f.mkdir();
			}

		};
		
	}
	
	

}
