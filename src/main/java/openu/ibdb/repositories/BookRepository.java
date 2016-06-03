package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.Author;
import openu.ibdb.models.Book;

//This annotation is responsible for exposing this repository interface as a RESTFul resource.
@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Integer> {
	Collection<Book> findByName(String name);
	Collection<Book> findByAuthor(Author author);
//	Collection<Book> findTop10ByReviews();
}
