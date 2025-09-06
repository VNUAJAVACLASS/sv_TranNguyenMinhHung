package services;

import java.util.List;

import dao.BookDAO;
import model.Book;

public class BookService {
	private BookDAO dao;
	
	public BookService() {
		dao = new BookDAO();
	}
	
	public List<Book> getAllBooks() {
		return dao.getAllBooks();
	}
	public boolean addBook(Book book) {
		return dao.addBook(book);
	}
	public Book findById(int id) {
		return dao.findById(id);
	}
	public boolean updateBook(Book book) {
		return dao.update(book);
	}
	public boolean deleteBook(int id) {
	    return dao.delete(id);
	}
}
