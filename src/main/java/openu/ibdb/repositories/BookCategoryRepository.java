package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.BookCategory;

/**
 * This annotation is responsible for exposing this repository interface as a RESTFul resource.
 * @author gulevy
 *
 */
@RepositoryRestResource
public interface BookCategoryRepository extends CrudRepository<BookCategory, Integer> {
	Collection<BookCategory> findByName(String name);
}
