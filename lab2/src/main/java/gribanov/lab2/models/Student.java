package gribanov.lab2.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gribanov.lab2.serializers.StudentSerializer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student")
@Data
@JsonSerialize(using = StudentSerializer.class)
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
