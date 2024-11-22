package repository;


import model.Librarian;

import javax.persistence.EntityManager;

public class LibrarianRepositoryImpl extends BaseRepositoryImpl<Librarian> {
    public LibrarianRepositoryImpl(EntityManager entityManager) {
        super(Librarian.class);
        this.entityManager = entityManager;
    }
}
