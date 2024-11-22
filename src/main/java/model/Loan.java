package model;

import lombok.Getter;
import lombok.Setter;
import model.base.BaseModel;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Loan extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "member_id")
    private LibraryMember member;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Temporal(TemporalType.DATE)
    @Column(name = "loan_date")
    private Date loanDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;
}
