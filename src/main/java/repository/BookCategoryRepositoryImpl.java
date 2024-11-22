package repository;

import model.BookCategory;

import javax.persistence.EntityManager;

public class BookCategoryRepositoryImpl extends BaseRepositoryImpl<BookCategory> {
    public BookCategoryRepositoryImpl(EntityManager entityManager) {
        super(BookCategory.class);
        this.entityManager = entityManager;
    }

}
