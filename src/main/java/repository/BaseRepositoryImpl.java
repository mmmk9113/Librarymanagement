package repository;

import model.Book;
import model.BookCategory;
import model.base.BaseModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {

    protected EntityManager entityManager;
    private final Class<T> entityClass;

    protected BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T entity) {

        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (((BaseModel) entity).getId() == null) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        {
            EntityTransaction transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                List<Book> books = entityManager.createQuery("SELECT b FROM Book b WHERE b.category.id = :categoryId", Book.class).setParameter("categoryId", id).getResultList();
                for (Book book : books) {
                    book.setCategory(null);
                    entityManager.merge(book);
                }
                BookCategory category = entityManager.find(BookCategory.class, id);
                if (category != null) {
                    for (Book book : category.getBooks()) {
                        book.setCategory(null);
                    }
                    entityManager.remove(category);
                }
                transaction.commit();
            } catch (RuntimeException e) {
                transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
}