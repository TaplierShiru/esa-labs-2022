package gribanov.lab2.models;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="teacher")
@Data
public class Teacher implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="fio")
    private String fio;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

}
