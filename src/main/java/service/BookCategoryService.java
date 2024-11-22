package service;

import model.BookCategory;
import repository.BookCategoryRepositoryImpl;

import java.util.Optional;

public class BookCategoryService {


    private final BookCategoryRepositoryImpl bookCategoryRepository;

    public BookCategoryService(BookCategoryRepositoryImpl bookCategoryRepository) {
        this.bookCategoryRepository = bookCategoryRepository;
    }

    public BookCategory addCategory(BookCategory category) {
        bookCategoryRepository.save(category);
        return category;
    }

    public void deleteCategory(Long id) {
        bookCategoryRepository.delete(id);
    }

    public BookCategory updateCategory(BookCategory category) {
        Optional<BookCategory> existingCategory = bookCategoryRepository.findById(category.getId());
        if (existingCategory.isPresent()) {
            BookCategory updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            bookCategoryRepository.save(updatedCategory);
            return updatedCategory;
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    public Iterable<BookCategory> getAllCategories() {
        return bookCategoryRepository.findAll();
    }

    public Iterable<BookCategory> getCategoriesWithBooks() {
        return bookCategoryRepository.findAll();
    }

    public Optional<BookCategory> findById(Long id) {
        return bookCategoryRepository.findById(id);
    }
}
