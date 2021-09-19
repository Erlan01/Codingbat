package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDto {

    @NotNull
    private Long languageId;

    @NotNull
    private String code;

    @NotNull
    private String description;

    @NotNull
    private String solution;
}
