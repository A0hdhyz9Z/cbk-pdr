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
@ApiModel(value="Accuracy对象", description="")
public class Accuracy implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double accuracy1;

    private Double accuracy2;

    private Double accuracy3;

    private Double accuracy4;

    private Double accuracy5;

    private Double accuracy6;

    private Double accuracy7;

    private Double accuracy8;

    private Double accuracy9;

    private Double accuracy10;


}
