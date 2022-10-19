package gribanov.lab1.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sessionData")
@Data
@NamedQueries({
        @NamedQuery(name="SessionData.getAll", query = "select s from SessionData s"),
        @NamedQuery(name="SessionData.getById", query = "select s from SessionData s where s.id = :id"),
        @NamedQuery(name="SessionData.deleteById", query = "delete from SessionData s where s.id = :id")
})
public class SessionData implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="mark")
    private int mark;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="studentId")
    private Student student;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="disciplineId")
    private Discipline discipline;
}
