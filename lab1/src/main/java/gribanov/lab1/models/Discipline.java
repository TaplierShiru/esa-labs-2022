package gribanov.lab1.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="discipline")
@Data
@NamedQueries({
        @NamedQuery(name="Discipline.getAll", query = "select d from Discipline d left join fetch d.teacher"),
        @NamedQuery(name="Discipline.getById", query = "select d from Discipline d" +
                " left join fetch d.teacher where d.id = :id"),
        @NamedQuery(name="Discipline.deleteById", query = "delete from Discipline d where d.id = :id")
})
public class Discipline implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @Column(name="typePass")
    private String typePass;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="teacherId")
    private Teacher teacher;
}
