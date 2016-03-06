package openu.ibdb.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import openu.ibdb.models.User;

//This annotation is responsible for exposing this repository interface as a RESTFul resource.
@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, Integer> {
	Collection<User> findByName(String name);
	User findByUsername(String username);
}
