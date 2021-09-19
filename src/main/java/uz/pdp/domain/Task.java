package uz.pdp.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "language_id",nullable = false)
    private Language language;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "solution")
    private String solution;
}
