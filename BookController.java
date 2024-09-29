package com.task.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.task.Model.Book;
import com.task.Service.BookService;

@RestController
@RequestMapping("/book/api")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/add")
	public ResponseEntity<Book> AddBook(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.AddBook(book), HttpStatus.CREATED);

	}

	@GetMapping("/getall")
	public ResponseEntity<List<Book>> getAllBook(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		List<Book> list = bookService.getAllBook(page, size);
		System.out.println(list.isEmpty());
		if (!list.isEmpty()) {
			return new ResponseEntity<>(bookService.getAllBook(page, size), HttpStatus.FOUND);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book are not Available..");
		}
	}

	@DeleteMapping("/delete/{isbn}")
	public ResponseEntity<Book> deleteByIsbn(@PathVariable String isbn) {

		return new ResponseEntity<>(bookService.deleteBook(isbn), HttpStatus.OK);

	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {

		return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Book> getById(@PathVariable int id) {
		return new ResponseEntity<>(bookService.getById(id), HttpStatus.FOUND);
	}

	@GetMapping("/get")
	public ResponseEntity<Book> getByIsbnAndTitle(@PathVariable String isbn, @RequestParam String title) {
		return new ResponseEntity<>(bookService.getByIsbnAndName(isbn, title), HttpStatus.FOUND);
	}

}
