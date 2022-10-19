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
@Table(name="student")
@Data
@NamedQueries({
        @NamedQuery(name="Student.getAll", query = "select s from Student s"),
        @NamedQuery(name="Student.getById", query = "select s from Student s" +
                " left join fetch s.sessionSet where s.id = :id"),
        @NamedQuery(name="Student.deleteById", query = "delete from Student s where s.id = :id")
})
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="numberRecordBook")
    private String numberRecordBook;

    @Column(name="fio")
    private String fio;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="groupId")
    private StudentGroup studentGroup;

    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<SessionData> sessionSet = new HashSet();

    public void addSessionData(SessionData sessionData){
        this.sessionSet.add(sessionData);
        sessionData.setStudent(this);
    }

    public void removeSessionData(SessionData sessionData){
        this.sessionSet.remove(sessionData);
    }
}
