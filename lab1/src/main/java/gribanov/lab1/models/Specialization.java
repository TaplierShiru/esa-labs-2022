package gribanov.lab1.models;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="specialization")
@Data
@NamedQueries({
        @NamedQuery(name="Specialization.getAll", query = "select s from Specialization s left join fetch s.groupSet"),
        @NamedQuery(name="Specialization.getById", query = "select s from Specialization s" +
                " left join fetch s.groupSet where s.id = :id"),
        @NamedQuery(name="Specialization.deleteById", query = "delete from Specialization s where s.id = :id")
})
public class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="specialization", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<StudentGroup> groupSet = new HashSet();

    public void addGroup(StudentGroup group){
        this.groupSet.add(group);
        group.setSpecialization(this);
    }
}
