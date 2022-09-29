package team.cbk.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Alg实体类", description="")
public class Alg implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单编号")
    private Integer orderId;
    @ApiModelProperty(value = "算法名(用整数标识,1代表第一种算法，2代表第二种，以此类推)")
    private Integer algName;
    @ApiModelProperty(value = "auc值")
    private Double auc;
    @ApiModelProperty(value = "roc存储位置")
    private String rocAdress;
    @ApiModelProperty(value = "f1分数")
    private Double f1Score;
    @ApiModelProperty(value = "精度")
    private Double precission;
    @ApiModelProperty(value = "召回率")
    private Double recall;
    @ApiModelProperty(value = "准确率")
    private Double accuracy;
}
