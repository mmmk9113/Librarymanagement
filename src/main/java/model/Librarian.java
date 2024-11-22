package model;

import lombok.Getter;
import lombok.Setter;
import model.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Librarian extends BaseModel {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "employee_id", unique = true)
    private String employeeId;
}
