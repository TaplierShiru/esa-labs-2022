package gribanov.lab2.models;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import gribanov.lab2.serializers.SpecializationSerializer;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="specialization")
@Data
@JsonSerialize(using = SpecializationSerializer.class)
public class Specialization implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="code")
    private String code;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="specialization", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<StudentGroup> groupSet = new HashSet();

    public void addGroup(StudentGroup group){
        this.groupSet.add(group);
        group.setSpecialization(this);
    }
}
