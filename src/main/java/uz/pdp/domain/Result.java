package uz.pdp.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "task_id",nullable = false)
    private Task task;

    @Column(name = "response_result",nullable = false)
    private String responseResult;

    @Column(name = "is_correct",nullable = false)
    private boolean isCorrect;
}
