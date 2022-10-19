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
@Table(name="studentGroup")
@Data
@NamedQueries({
        @NamedQuery(name="StudentGroup.getAll", query = "select s from StudentGroup s" +
                " left join fetch s.studentSet"),
        @NamedQuery(name="StudentGroup.getById", query = "select s from StudentGroup s" +
                " left join fetch s.specialization left join fetch s.studentSet where s.id = :id"),
        @NamedQuery(name="StudentGroup.deleteById", query = "delete from StudentGroup s where s.id = :id")
})
public class StudentGroup implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="course")
    private int course;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="specializationId")
    private Specialization specialization;

    @OneToMany(mappedBy="studentGroup", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Student> studentSet = new HashSet();

    public void addStudent(Student student){
        this.studentSet.add(student);
        student.setStudentGroup(this);
    }
}
