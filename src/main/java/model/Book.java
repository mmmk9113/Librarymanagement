package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.base.BaseModel;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Book extends BaseModel {

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private BookCategory category;


}
