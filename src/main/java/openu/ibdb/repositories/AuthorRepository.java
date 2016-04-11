package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.Author;

//This annotation is responsible for exposing this repository interface as a RESTFul resource.
@RepositoryRestResource
public interface AuthorRepository extends CrudRepository<Author, Integer> {
	Collection<Author> findByAuthorId(int authorId);
	Collection<Author> findByFirstName(String firstName);
}
