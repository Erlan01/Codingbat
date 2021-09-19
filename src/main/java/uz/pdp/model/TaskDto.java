package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long languageId;

    private String code;

    private String description;

    private String solution;
}
