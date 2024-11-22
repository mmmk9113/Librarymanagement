package repository;

import model.Book;

import javax.persistence.EntityManager;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book> {
    public BookRepositoryImpl(EntityManager entityManager) {
        super(Book.class);
        this.entityManager = entityManager;
    }

    public Iterable<Book> findByCategoryId(Long categoryId) {
        return null;
    }
}
