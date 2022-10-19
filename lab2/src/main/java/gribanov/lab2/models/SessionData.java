package gribanov.lab2.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gribanov.lab2.serializers.SessionDataSerializer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="sessionData")
@Data
@JsonSerialize(using = SessionDataSerializer.class)
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
