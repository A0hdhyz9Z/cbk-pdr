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
@ApiModel(value="Auc对象", description="")
public class Auc implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double auc1;

    private Double auc2;

    private Double auc3;

    private Double auc4;

    private Double auc5;

    private Double auc6;

    private Double auc7;

    private Double auc8;

    private Double auc9;

    private Double auc10;


}
