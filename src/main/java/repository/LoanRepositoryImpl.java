package repository;

import model.Loan;

import javax.persistence.EntityManager;

public class LoanRepositoryImpl extends BaseRepositoryImpl<Loan>{
    public LoanRepositoryImpl(EntityManager entityManager) {
        super(Loan.class);
        this.entityManager = entityManager;
    }
}
