package model;

import lombok.Getter;
import lombok.Setter;
import model.base.BaseModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter

public class BookCategory extends BaseModel {

    @Column(name = "name", nullable = false)
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private Set<Book> books = new HashSet<>();

}
