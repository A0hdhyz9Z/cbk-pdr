package team.cbk.demo.pojo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cyt
 * @since 2022-10-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="F1score对象", description="")
public class F1score implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double f1Score1;

    private Double f1Score2;

    private Double f1Score3;

    private Double f1Score4;

    private Double f1Score5;

    private Double f1Score6;

    private Double f1Score7;

    private Double f1Score8;

    private Double f1Score9;

    private Double f1Score10;


}
