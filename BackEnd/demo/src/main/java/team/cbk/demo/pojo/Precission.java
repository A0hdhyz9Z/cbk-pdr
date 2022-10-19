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
@ApiModel(value="Precission对象", description="")
public class Precission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Double precission1;

    private Double precission2;

    private Double precission3;

    private Double precission4;

    private Double precission5;

    private Double precission6;

    private Double precission7;

    private Double precission8;

    private Double precission9;

    private Double precission10;


}
