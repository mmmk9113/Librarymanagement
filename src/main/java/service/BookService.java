package service;

import model.Book;
import model.BookCategory;
import repository.BookCategoryRepositoryImpl;
import repository.BookRepositoryImpl;

import java.util.Optional;

public class BookService {

    private final BookRepositoryImpl bookRepository;
    private final BookCategoryRepositoryImpl bookCategoryRepository;

    public BookService(BookRepositoryImpl bookRepository, BookCategoryRepositoryImpl bookCategoryRepository) {
        this.bookRepository = bookRepository;
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public Book addBook(Book book) {
        Optional<BookCategory> category = bookCategoryRepository.findById(book.getCategory().getId());
        if (category.isPresent()) {
            book.setCategory(category.get());
            bookRepository.save(book);
            return book;
        } else {
            throw new IllegalArgumentException("Invalid category ID");
        }
    }

    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }

    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setTitle(book.getTitle());
            Optional<BookCategory> category = bookCategoryRepository.findById(book.getCategory().getId());
            if (category.isPresent()) {
                updatedBook.setCategory(category.get());
                bookRepository.save(updatedBook);
                return updatedBook;
            } else {
                throw new IllegalArgumentException("Invalid category ID");
            }
        } else {
            throw new IllegalArgumentException("Book not found");
        }
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Iterable<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
}