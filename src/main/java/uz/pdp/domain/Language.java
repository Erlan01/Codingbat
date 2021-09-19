package uz.pdp.entity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",nullable = false)
    private String  name;


    @Column(name = "status", columnDefinition = "boolean default true")
    private boolean status;


}
