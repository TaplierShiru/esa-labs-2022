package gribanov.lab2.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gribanov.lab2.serializers.DisciplineSerializer;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="discipline")
@Data
@JsonSerialize(using = DisciplineSerializer.class)
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
