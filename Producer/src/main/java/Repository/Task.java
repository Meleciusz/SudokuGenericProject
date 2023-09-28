package Repository;//package Repository;
//
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Entity(name = "Tasks")
//@Table(
//        name = "tasks",
//        uniqueConstraints = {
//                @UniqueConstraint(
//                    name = "tasks_name_unique", columnNames = "task"
//                )
//        }
//)
//public class Task {
//
//    @Id
//    @SequenceGenerator(
//            name = "tasks_sequence",
//            sequenceName = "tasks_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//        strategy = GenerationType.SEQUENCE,
//        generator = "tasks_sequence"
//    )
//    @Column(
//        name = "id"
//    )
//    @Getter @Setter
//    private int Id;
//
//    @Column(
//        name = "task"
//    )
//    @Getter @Setter
//    private String task;
//
//    @Column(
//        name = "state"
//    )
//    @Getter @Setter
//    private String state;
//
//    public Task(String task, String state) {
//        this.task = task;
//        this.state = state;
//    }
//}


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Task {
    private static int lastAssignedID = 0;

    @Getter @Setter
    private int ID = 0;

    @Getter @Setter
    private String task;

    @Getter @Setter
    private String state;

    public Task(String task, String state) {
        this.ID = ++lastAssignedID;
        this.task = task;
        this.state = state;
    }

}