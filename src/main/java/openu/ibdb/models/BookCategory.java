package openu.ibdb.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "book_category")
public class BookCategory {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	@OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)

	@JsonBackReference
	private Set<Book> books;

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
//	@Override
//	public String toString() {
//	        String result = String.format(
//	                "Category[id=%d, name='%s']%n",
//	                id, name);
//	        if (books != null) {
//	            for(Book book : books) {
//	                result += String.format(
//	                        "Book[id=%d, name='%s']%n",
//	                        book.getBookId(), book.getName());
//	            }
//	        }
//
//	        return result;
//	    }
}
