package gribanov.lab2.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gribanov.lab2.serializers.StudentGroupSerializer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="studentGroup")
@Data
@JsonSerialize(using = StudentGroupSerializer.class)
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
