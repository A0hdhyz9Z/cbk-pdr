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
@ApiModel(value="Recall对象", description="")
public class Recall implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String recall1;

    private String recall2;

    private String recall3;

    private String recall4;

    private String recall5;

    private String recall6;

    private String recall7;

    private String recall8;

    private String recall9;

    private String recall10;


}
