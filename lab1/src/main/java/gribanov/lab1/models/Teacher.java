package gribanov.lab1.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Why use `left join fetch`
// https://thorben-janssen.com/lazyinitializationexception/

@Entity
@Table(name="teacher")
@Data
@NamedQueries({
        @NamedQuery(name="Teacher.getAll", query = "select t from Teacher t"),
        @NamedQuery(name="Teacher.getById", query = "select t from Teacher t where t.id = :id"),
        @NamedQuery(name="Teacher.deleteById", query = "delete from Teacher t where t.id = :id")
})
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
