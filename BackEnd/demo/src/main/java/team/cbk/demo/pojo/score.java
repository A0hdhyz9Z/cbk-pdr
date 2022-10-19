package team.cbk.demo.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="score实体类", description="")
public class score {
    Double accuracy1;
    Double accuracy2;
    Double accuracy3;
    Double accuracy4;
    Double accuracy5;
    Double accuracy6;
    Double accuracy7;
    Double accuracy8;
    Double accuracy9;
    Double accuracy10;

    Double precission1;
    Double precission2;
    Double precission3;
    Double precission4;
    Double precission5;
    Double precission6;
    Double precission7;
    Double precission8;
    Double precission9;
    Double precission10;

    Double recall1;
    Double recall2;
    Double recall3;
    Double recall4;
    Double recall5;
    Double recall6;
    Double recall7;
    Double recall8;
    Double recall9;
    Double recall10;

    Double f1Score1;
    Double f1Score2;
    Double f1Score3;
    Double f1Score4;
    Double f1Score5;
    Double f1Score6;
    Double f1Score7;
    Double f1Score8;
    Double f1Score9;
    Double f1Score10;

    Integer id;
}
