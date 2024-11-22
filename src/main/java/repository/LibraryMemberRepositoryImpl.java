package repository;

import model.LibraryMember;

import javax.persistence.EntityManager;

public class LibraryMemberRepositoryImpl extends BaseRepositoryImpl<LibraryMember>{
    public LibraryMemberRepositoryImpl(EntityManager entityManager) {
        super(LibraryMember.class);
        this.entityManager = entityManager;
    }
}
