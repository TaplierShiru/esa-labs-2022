package gribanov.lab2.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "action_watcher")
public class ActionWatcher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column
    private String action;

    @Column
    private String entity;

    @Column
    private String substance;

    public ActionWatcher(String action, String entity, String substance) {
        this.action = action;
        this.entity = entity;
        this.substance = substance;
    }

}
