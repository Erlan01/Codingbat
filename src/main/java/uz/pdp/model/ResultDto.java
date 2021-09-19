package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ResultDto {

    private Long userId;

    private Long taskId;

    private String responseResult;

    private boolean isCorrect;
}
